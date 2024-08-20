package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.UserDTO;

public class LoginCheckInterceptor implements HandlerInterceptor{
	
	private UserDTO loginUserDTO;
	
	/*
	  Java 설정 방식에서는 Interceptor 에서 주입받지 못하므로
	  ServletAppContext.java 에서 LoginCheckInterceptor 의 객체를 생성할 때
	  loginUserDTO 를 주입받아서 argument 로 넣어줌
	*/
	public LoginCheckInterceptor(UserDTO loginUserDTO) {
		this.loginUserDTO = loginUserDTO;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if(loginUserDTO.isUserIsLogin() == false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/cant_login");
			return false;
		}
		return true;
	}
	
	

}
