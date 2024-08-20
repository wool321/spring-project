package com.tjoeun.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dto.ContentDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.dto.UserDTO;
import com.tjoeun.dto.PageDTO;

@Service
@PropertySource("/WEB-INF/properties/upload.properties")
public class BoardService {
	// 페이지 네비게이터에 나오는 페이지 (버튼) 개수
	@Value("${page.countOfPagination}") 
	private int countOfPagination;
	
	// 한 페이지 당 게시글 개수
	@Value("${page.countPerPage}") 
  private int countPerPage;
	
	@Value("${path.upload}")
	private String pathUpload;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Resource(name="loginUserDTO")
	private UserDTO loginUserDTO;
	
	private String saveUploadFile(MultipartFile multipartFile){
		// multipartFile 에서 원본파일 이름을 가져와서 currentTimeMillis() 와 합쳐서 이름을 만듬
		// upload 할 때의 파일 이름 (<-- 원본 파일이름이 아님)
		String originalFileName = multipartFile.getOriginalFilename();
		String uploadFileName 
		  = System.currentTimeMillis() + "_" + originalFileName;
		try {
			multipartFile.transferTo(new File(pathUpload + "/" + uploadFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		return uploadFileName;
	}
	
	public void addContent(ContentDTO writeContentDTO) {
		/*
		System.out.println("제 목 : " + writeContentDTO.getContent_subject());
		System.out.println("내 용 : " + writeContentDTO.getContent_text());
		System.out.println("파 일 : " + writeContentDTO.getUpload_file().getSize());
		*/
		/* writeContentDTO 의 멤버변수로 
		   private MultipartFile uploadFile 이 선언되어 있음
		*/
		MultipartFile uploadFile = writeContentDTO.getUploadFile();
		
		// uploadFile.getSize() > 0 : file 을 upload 했다면...
		if(uploadFile.getSize() > 0) {
		  String uploadFileName = saveUploadFile(uploadFile);
		  System.out.println("업로드한 파일 이름 : " + uploadFileName);
		  writeContentDTO.setContent_file(uploadFileName);
		}
		
		// 현재 로그인한 회원의 index 번호를
		// 게시글 정보를 저장하는 CONTENT_TABLE 의 
		// content_writer_idx 컬럼에 저장하려고 
		// writeContentDTO 의 content_writer_idx 에 저장함
		writeContentDTO.setContent_writer_idx(loginUserDTO.getUser_idx());
		
		boardDAO.insertContent(writeContentDTO);
		
	}
	
	public String getBoardName(int board_info_idx) {
    return boardDAO.getBoardName(board_info_idx); 	
  }
	 
	  public List<ContentDTO> getContentList(int content_board_idx, int page){
	  	
	  	int startPage = (page - 1) * this.countPerPage;
	  	// org.apache.ibatis.session.RowBounds.RowBounds(int offset, int limit)
	  	RowBounds rowBounds = new RowBounds(startPage, countPerPage);
	  	
	  	List<ContentDTO> contentList = boardDAO.getContentList(content_board_idx, rowBounds);
	  	return contentList;
	  }
	  
    public ContentDTO getContent(int content_idx) {
	  	return boardDAO.getContent(content_idx);
	  }	 
    
    public void deleteContent(int content_idx) {
    	boardDAO.deleteContent(content_idx);
    }
    
    public void updateContent(ContentDTO modifyContentDTO) {
    	MultipartFile uploadFile = modifyContentDTO.getUploadFile();
    	
    	if(uploadFile.getSize() > 0) {
  		  String uploadFileName = saveUploadFile(uploadFile);
  		  System.out.println("업로드한 파일 이름 : " + uploadFileName);
  		  modifyContentDTO.setContent_file(uploadFileName);
  		}
    	boardDAO.updateContent(modifyContentDTO);
    }
    
    public PageDTO getCountOfContentPerBoard(int content_board_idx, int currentPage) {
    	// content_board_idx(1 ~ 4) 번호에 해당하는 게시글 전체 개수 
    	int countOftotalContent = boardDAO.getCountOfContentPerBoard(content_board_idx);
    	
    	PageDTO pageDTO = 
    			 new PageDTO(countOftotalContent, currentPage, countOfPagination, countPerPage);
    	
    	return pageDTO;
    }  
	 
}
