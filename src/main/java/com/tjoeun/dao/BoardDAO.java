package com.tjoeun.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.dto.ContentDTO;
import com.tjoeun.mapper.BoardMapper;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper boardMapper;
	
  public void insertContent(ContentDTO writeContentDTO) {
  	 for(int i = 0; i < 362; i++) {
  	  boardMapper.insertContent(writeContentDTO);
  	}
  	// boardMapper.insertContent(writeContentDTO);
  }
            
  public String getBoardName(int board_info_idx) {
    return boardMapper.getBoardName(board_info_idx); 	
  }
  
  public List<ContentDTO> getContentList(int content_board_idx, RowBounds rowBounds){
  	List<ContentDTO> contentList = boardMapper.getContentList(content_board_idx, rowBounds);
  	return contentList;
  }
  
  public ContentDTO getContent(int content_idx) {
  	return boardMapper.getContent(content_idx);
  }
  
  public void deleteContent(int content_idx) {
  	boardMapper.deleteContent(content_idx);
  }
  public void updateContent(ContentDTO modifyContentDTO) {
  	boardMapper.updateContent(modifyContentDTO);
  }
  
  public int getCountOfContentPerBoard(int content_board_idx) {
  	return boardMapper.getCountOfContentPerBoard(content_board_idx);
  }
  
  
}




