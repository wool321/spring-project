package com.tjoeun.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.UserDTO;
import com.tjoeun.mapper.UserMapper;

@Repository
public class UserDAO {

	@Autowired
	private UserMapper userMapper;
	
	public String checkUserIdExist(String user_id) {
		String user_name = userMapper.checkUserIdExist(user_id);
		return user_name;
	}
	
	public void insertUser(UserDTO joinUserDTO) {
		userMapper.insertUser(joinUserDTO);
	}
	
	public UserDTO getLoginUserInfo(UserDTO validationLoginUserDTO) {
		return userMapper.getLoginUserInfo(validationLoginUserDTO);
	}
	
	public UserDTO getModifyUserInfo(int user_idx) {
		return userMapper.getModifyUserInfo(user_idx);
	}
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		userMapper.modifyUserInfo(modifyUserDTO);
	}
	
	
}
