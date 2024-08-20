package com.tjoeun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.BeanTestDAO;

@Service
public class BeanTestService {
	
	@Autowired
	BeanTestDAO beanTestDAO;
	
  public String method1() {
  	String str1 = beanTestDAO.method1();
  	System.out.printf("str1 : %s%n", str1);
  	return "BeanTestService";
  }
}

