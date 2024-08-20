package com.tjoeun.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.dto.ContentDTO;
import com.tjoeun.dto.PageDTO;
import com.tjoeun.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
			               @RequestParam(value="page", defaultValue="1") int page,
			               Model model) {
		model.addAttribute("board_info_idx", board_info_idx);		
		String boardName = boardService.getBoardName(board_info_idx);
		model.addAttribute("boardName", boardName);
		List<ContentDTO> contentList = boardService.getContentList(board_info_idx, page);
		model.addAttribute("contentList", contentList );
		
		PageDTO pageDTO = boardService.getCountOfContentPerBoard(board_info_idx, page);
		model.addAttribute("pageDTO", pageDTO);
		
		model.addAttribute("page", page);
		
		return "board/main";      
	}
	       
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx, 
			               @RequestParam("content_idx") int content_idx,
			               @RequestParam("page") int page,
			               Model model) {    
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		ContentDTO readContentDTO = boardService.getContent(content_idx);
		model.addAttribute("readContentDTO", readContentDTO);
		
		model.addAttribute("page", page);
		
		return "board/read";
	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentDTO") ContentDTO writeContentDTO,
			                @RequestParam("board_info_idx") int board_info_idx) {
		writeContentDTO.setContent_board_idx(board_info_idx);
		
		return "board/write";
	}
	
	@PostMapping("/write_procedure")
	public String writeProcedure(@Valid @ModelAttribute("writeContentDTO") ContentDTO writeContentDTO, BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";	
		}
		
		boardService.addContent(writeContentDTO);
		System.out.println("writeContentDTO (controller) : " + writeContentDTO);
		
		return "board/write_success";
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx, 
			                 @RequestParam("content_idx") int content_idx,
			                 @RequestParam("page") int page,
			                 @ModelAttribute("modifyContentDTO") ContentDTO modifyContentDTO,
			                 Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("page", page);
		
    ContentDTO tmpContentDTO = boardService.getContent(content_idx);
		modifyContentDTO.setContent_writer_name(tmpContentDTO.getContent_writer_name());
		modifyContentDTO.setContent_date(tmpContentDTO.getContent_date());
		modifyContentDTO.setContent_subject(tmpContentDTO.getContent_subject());
		modifyContentDTO.setContent_text(tmpContentDTO.getContent_text());
		modifyContentDTO.setContent_file(tmpContentDTO.getContent_file());
		modifyContentDTO.setContent_writer_idx(tmpContentDTO.getContent_writer_idx());
		
		/*
		  BoardMapper 의 void getContent(content_idx) 에서
		  content_board_idx 와 content_idx 는 select 하지 않았기 때문에
		  tmpContentDTO.getContent_board_idx() 와 
		  tmpContentDTO.getContent_idx() 는 값이 setting 이 안 되어 있음
		  여기서는 tmpContentDTO.getContent_board_idx(), tmpContentDTO.getContent_idx() 를
		  사용하지 말고 board_info_idx, content_idx 를 사용해야 함
		*/
		modifyContentDTO.setContent_board_idx(board_info_idx);
		modifyContentDTO.setContent_idx(content_idx);
				
		return "board/modify";
	}
	
	@PostMapping("/modify_procedure")
	public String modify_procedure(@Valid @ModelAttribute("modifyContentDTO") ContentDTO modifyContentDTO,
			                           BindingResult result, @RequestParam("page") int page, Model model){
		    
		if(result.hasErrors()) {
			// System.out.println("result.hasErrors() is true");
			return "board/modify";
		}         
		
		boardService.updateContent(modifyContentDTO);
		model.addAttribute("page", page);
		
		return "board/modify_success";
	}  

	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
			                 @RequestParam("content_idx") int content_idx,
			                 Model model) {
		boardService.deleteContent(content_idx);
		model.addAttribute("board_info_idx",board_info_idx);
		return "board/delete";
	}
	
	@GetMapping("/cant_write")
	public String cant_write() {
		return "board/cant_write";
	}
	
}
