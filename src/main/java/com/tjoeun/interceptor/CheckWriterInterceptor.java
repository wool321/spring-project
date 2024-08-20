package com.tjoeun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.dto.ContentDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	
	/*
	  UserDTO 와 BoardService 를 사용하기 위해서
	  멤버변수로 UserDTO 와 BoardService 타입의 변수를 선언함
	  
	  Java 설정 방식에서는 Interceptor 에서는 주입받을 수 없으므로
	  ServletAppContext.java 에서 주입받아서
	  CheckWriterInterceptor 객체를 생성할 때
	  생성자의 parameter 로 전달받아서 사용함
	*/
	private UserDTO loginUserDTO;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserDTO loginUserDTO, BoardService boardService) {
		this.loginUserDTO = loginUserDTO;
		this.boardService = boardService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		int contentIdx = Integer.parseInt(request.getParameter("content_idx"));
		ContentDTO contentDTO = boardService.getContent(contentIdx);
		
		if(contentDTO.getContent_writer_idx() != loginUserDTO.getUser_idx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/cant_write");
			return false;
		}
		
		return true;
		
	}
	
	
	
	
	
	

}
