package com.tjoeun.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
	private int user_idx;
	
	@Size(min=2, max=4)
	@Pattern(regexp="[가-힣]*")
	private String user_name;

	@Size(min=4, max=30)
	@Pattern(regexp="[a-zA-Z0-9]*")
	private String user_id;

	@Size(min=4, max=30)
	@Pattern(regexp="[a-zA-Z0-9]*")
	private String user_pw;

	@Size(min=4, max=30)
	@Pattern(regexp="[a-zA-Z0-9]*")
	private String user_pw2;
	
	private boolean userIdExist;
	
	private boolean userIsLogin;
	
	
	
}


