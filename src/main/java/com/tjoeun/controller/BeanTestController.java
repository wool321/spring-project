package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjoeun.service.BeanTestService;

@Controller
public class BeanTestController {
	
	@Autowired
	BeanTestService beanTestService;
	
  @GetMapping("/test1")
  public String test1(Model model) {
  	String result = beanTestService.method1();
  	System.out.printf("result : %s%n ", result);
  	
  	model.addAttribute("result", result);  	
  	return "test1";
  }
}
