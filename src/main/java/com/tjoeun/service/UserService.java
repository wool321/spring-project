package com.tjoeun.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.dto.UserDTO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	// Session Scope 에 생성된 UserDTO 주입받기
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	
	public boolean checkUserIdExist(String user_id) {
		String user_name = userDAO.checkUserIdExist(user_id);
		
		if(user_name == null) {
		  return true;
		}
	  return false;
	}

	public void insertUser(UserDTO joinUserDTO) {
		userDAO.insertUser(joinUserDTO);
	}
	
	public UserDTO getLoginUserInfo(UserDTO validationLoginUserDTO) {
		
		// 로그인한 회원의 정보를 Database로부터 가져와서 (parameter 로 전달 받음)
		UserDTO tmpUserDTO = userDAO.getLoginUserInfo(validationLoginUserDTO);
		
		// Session Scope 에 생성된 UserDTO 에 저장함
		if(tmpUserDTO != null) {
			loginUserDTO.setUser_idx(tmpUserDTO.getUser_idx());
			loginUserDTO.setUser_name(tmpUserDTO.getUser_name());
			loginUserDTO.setUserIsLogin(true);		
		}
		
		return tmpUserDTO;
	}
	
	public void getModifyUserInfo(UserDTO modifyUserDTO) {
		// session scope 에 저장된 로그인한 회원의 정보에서
		// user_idx 를 가져와서 
		int user_idx = loginUserDTO.getUser_idx();
		// 이 값을 (user_idx)
		// userDAO.getModiyUserInfo(user_idx) 를 호출하면서 argument 로 넣어줌
		UserDTO loggedInUserDTO =  userDAO.getModifyUserInfo(user_idx);
		// 로그인한 회원 idx 로 DB 에서 조회한 결과를 가져와서
		// Controller 로부터 전달받은 modifyUserDTO 객체에 값을 설정함
		//  로그인한 회원 정보로 설정된 modifyUserDTO 객체는 Controller 에서 사용하며
		// user/modify.jsp 화면에서 modelAttribute="modifyUserDTO" 부분에서 받아서 사용함
		modifyUserDTO.setUser_id(loggedInUserDTO.getUser_id());
		modifyUserDTO.setUser_name(loggedInUserDTO.getUser_name());
		modifyUserDTO.setUser_idx(user_idx );
		
	}
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		// session scope 에서 로그인한 사람의 정보를 저장하고 있는
		// loginUserDTO에서 user_idx 를 가져와서
		// Controller 로부터 전달받은 modifyUserDTO 의 user_idx 에 저장함
		// modifyUserDTO.setUser_idx(loginUserDTO.getUser_idx());

		// 윗 행에서 setting 된 modifyUserDTO 를 
		// DAO 의 modifyUserInfo() 메소드의 parameter 에 전달함 
		userDAO.modifyUserInfo(modifyUserDTO);
	}
	
}


