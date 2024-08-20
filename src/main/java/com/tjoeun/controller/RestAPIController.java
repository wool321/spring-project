package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.service.UserService;

@RestController
public class RestAPIController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		
		boolean checkUserId = userService.checkUserIdExist(user_id);
		return "" + checkUserId;
		
	}
	
	
	
	
}

