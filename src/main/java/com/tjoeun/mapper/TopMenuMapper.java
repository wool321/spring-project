package com.tjoeun.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.tjoeun.dto.BoardInfoDTO;

public interface TopMenuMapper {
	
	@Select("SELECT * FROM BOARD_INFO_TABLE ORDER BY BOARD_INFO_IDX")
	List<BoardInfoDTO> getTopMenuList();

}







