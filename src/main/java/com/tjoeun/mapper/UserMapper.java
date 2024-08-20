package com.tjoeun.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tjoeun.dto.UserDTO;

public interface UserMapper {
	
	@Select("SELECT USER_NAME " +
          "FROM USER_TABLE " +
          "WHERE USER_ID=#{user_id}")
	String checkUserIdExist(String user_id);
	
	@Insert("INSERT INTO USER_TABLE " +
          "VALUES(USER_SEQ.NEXTVAL, #{user_name}, #{user_id}, #{user_pw})")
	void insertUser(UserDTO joinUserDTO);
	
	@Select("SELECT * " + 
          "FROM USER_TABLE " + 
          "WHERE USER_ID=#{user_id} AND USER_PW=#{user_pw}")
	UserDTO getLoginUserInfo(UserDTO validationLoginUserDTO);
	
	@Select("SELECT USER_ID, USER_NAME " +
          "FROM USER_TABLE " +
          "WHERE USER_IDX=#{user_idx}")
	UserDTO getModifyUserInfo(int user_idx);
	
	@Update("UPDATE USER_TABLE " + 
          "SET USER_PW=#{user_pw} " + 
          "WHERE USER_IDX =#{user_idx} ")	
	void modifyUserInfo(UserDTO modifyUserDTO);
	
	
	
}



