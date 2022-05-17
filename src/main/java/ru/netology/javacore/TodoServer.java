package ru.netology.javacore;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

	private final int port;
	private Todos todos;
	private String body = "";
	private char c = ' ';


	public TodoServer(int port, Todos todos) {
		this.port = port;
		this.todos = todos;
	}

	public void start() {
		System.out.println("Starting server at " + port + "...");
		try (ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()))) {

			while (true) {
				String taskJson = in.readLine();

				JsonElement rootNode = JsonParser.parseString(taskJson);
				JsonObject details = rootNode.getAsJsonObject();

				String type = details.get("type").getAsString();
				String task = details.get("task").getAsString();

				if (task.length() > 4){
					body = task.substring(task.indexOf(' ')).trim();
					c = body.charAt(0);
				}

				String todo;
				String digit;
				int index;
				switch (type) {
					case "ADD":
						if (Character.isDigit(c)){
							digit = body.substring(0, body.indexOf(' '));
							todo = body.substring(body.indexOf(' ')).trim();
							index = Integer.parseInt(digit);
							todos.add(index, todo);
							break;
						}
						todo = body;
						todos.add(todo);
						break;

					case "EDIT":
						digit = body.substring(0, body.indexOf(' '));
						todo = body.substring(body.indexOf(' ')).trim();
						index = Integer.parseInt(digit);
						todos.edit(todo, index);
						break;

					case "REMOVE":
						index = Integer.parseInt(body);
						todos.delete(index);
						break;

				}
				out.println(todos.getTodos());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
