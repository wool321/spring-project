package com.tjoeun.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.BoardInfoDTO;
import com.tjoeun.mapper.TopMenuMapper;

@Repository
public class TopMenuDAO {
	
	@Autowired
	private TopMenuMapper topMenuMapper;
	
	// DAO 의 메소드에서 TopMenuMapper 의 
	// getTopMenuList() 메소드를 호출함
	
	public List<BoardInfoDTO> getTopMenuList(){
		// return topMenuMapper.getTopMenuList();
  	List<BoardInfoDTO> topMenuList = topMenuMapper.getTopMenuList();
  	// System.out.println("topMenuList (dao) : " + topMenuList);
  	return topMenuList;
	}
	
	
}





