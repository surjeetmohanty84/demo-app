package com.example.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class HelloController {

	@GetMapping("/greet")
	public String getMorningMsg() {
		return "Good Night Greet!";
	}
	@RequestMapping(value = "/getdata",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUserData(){
		List<User> list=new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
		try {
			User[] users = mapper.readValue(inputStream,User[].class);
			for(User u: users) {
				list.add(u);
			}
			System.out.println("Users Saved!");
		} catch (Exception e){
			System.out.println("Unable to save users: " + e.getMessage());
		}
	
		return new ResponseEntity<List<User>>(list, HttpStatus.FOUND);
	}
}
