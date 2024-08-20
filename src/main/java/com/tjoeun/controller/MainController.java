package com.tjoeun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjoeun.dto.ContentDTO;
import com.tjoeun.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@GetMapping("/main")
	public String main(Model model) {

		List<List<ContentDTO>> boardList = new ArrayList<List<ContentDTO>>();
		for(int i = 1; i <= 4; i++) {
			List<ContentDTO> contentList = mainService.getMainBoardList(i);
			boardList.add(contentList);
		}
		model.addAttribute("boardList", boardList);
		
		return "main";
	}

}
