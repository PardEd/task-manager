package ru.netology.javacore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Программа для хранения списка дел")
public class TodosTests {

	private Todos todoList;

	private final String[] list = {"write issue", "buy milk",  "send email", "learn java", "create app"};

	private final List<String> testTodos = List.of("create app", "learn java", "send email", "write issue");

    @BeforeEach
	public void setUp(){
		todoList = new Todos();
    }

	@Test
	@DisplayName("Добавление одного дела")
	void addTodo(){
		String todo = "first added todo";
		todoList.add(todo);
		assertEquals (todo, todoList.getTodos());
	}

	@Test
	@DisplayName("Удаление дела")
	void deleteTodo(){
		for(String s : list){
			todoList.add(s);
		}
		todoList.delete(1);
		assertEquals(testTodos.stream().sorted().collect(Collectors.joining(", ")), todoList.getTodos());
	}

	@Test
	@DisplayName("Просмотр текущих дел")
	void printTodos(){
		for (String s : list){
		todoList.add(s);
		}
		String result = Arrays.stream(list).sorted().collect(Collectors.joining(", "));

		assertEquals(todoList.getTodos(), result);
	}
}
