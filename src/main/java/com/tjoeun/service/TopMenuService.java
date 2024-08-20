package com.tjoeun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.TopMenuDAO;
import com.tjoeun.dto.BoardInfoDTO;

@Service
public class TopMenuService {
	
	@Autowired
	TopMenuDAO topMenuDAO;  

	public List<BoardInfoDTO> getTopMenuList(){
		// return topMenuMapper.getTopMenuList();
  	List<BoardInfoDTO> topMenuList = topMenuDAO.getTopMenuList();
  	// System.out.println("topMenuList (service) : " + topMenuList);
  	return topMenuList;
	}
	
}



