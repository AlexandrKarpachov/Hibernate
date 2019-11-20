package ru.job4j.todo;




import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ru.job4j.todo.models.Item;
import ru.job4j.todo.service.Validator;


public class Test {
	
	
	public static void main(String[] args) throws JsonProcessingException {
		Validator validator = Validator.getInstance();
		List<Item> items = validator.getAll();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonItems = mapper.writeValueAsString(items);

		System.out.println(jsonItems);

		
	}
}
