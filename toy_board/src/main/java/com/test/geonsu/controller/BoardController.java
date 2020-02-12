package com.test.geonsu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.geonsu.domain.BoardVO;
import com.test.geonsu.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	//글작성 get
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrtie() {
		logger.info("get write");		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(BoardVO vo) {
		logger.info("post write");
		service.write(vo);
		
		return "redirect:/";
	}
	
	
}
