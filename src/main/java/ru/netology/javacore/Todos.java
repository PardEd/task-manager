package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Todos {

	public List<String> list = new ArrayList<>();

	public void add(String todo) {
		list.add(todo);
	}

	public void add(int index, String todo) {
		if (index > -1 && index < list.size()) {
			list.add(index, todo);
		} else {
			add(todo);
		}
	}

	public void edit(String todo, int index) {
		if (index > -1 && index < list.size()) {
			System.out.println(
				"Дело " + '"' + list.get(index) + '"' + " заменено на " + '"' + todo + '"');
			list.set(index, todo);
		}
	}

	public void delete(int index) {
		if (index > -1 && index < list.size()) {
			System.out.println("Дело " + '"' + list.get(index) + '"' + " удалено");
			list.remove(index);
		} else if (index > list.size()) {
			System.out.println("Дело с таким номером не сущетсвует");
		}
	}

	public String getTodos() {
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s).append(", ");
		}
		return StringUtils.substring(sb.toString(), 0, sb.toString().length() - 2);
	}
}