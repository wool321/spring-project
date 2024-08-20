package com.tjoeun.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ContentDTO {
	private int content_idx;
	
	@NotBlank
	private String content_subject;
	
	@NotBlank
	private String content_text;
	
	private MultipartFile uploadFile;
	
	private String content_file;
	private int content_writer_idx;
	private int content_board_idx;
	private String content_date;
	private String content_writer_name;
	
}
