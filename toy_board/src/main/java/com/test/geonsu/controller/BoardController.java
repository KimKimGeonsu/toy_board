package com.test.geonsu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.geonsu.domain.BoardVO;
import com.test.geonsu.domain.PageMaker;
import com.test.geonsu.domain.SearchCriteria;
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
	
	//글목록
	@RequestMapping(value = "/listSearch",method = RequestMethod.GET)
	public void list(Model model,@ModelAttribute("scri") SearchCriteria scri) {		
		logger.info("get 조회");	
		List<BoardVO> list = service.listSearch(scri);
		System.out.println("리스트의 반환"+list);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount());
		model.addAttribute("pageMaker",pageMaker);
	}
	
	//글상세
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void getRead(int bno, @ModelAttribute("scri") SearchCriteria scri,Model model) {
		logger.info("get 글 상세");
		BoardVO vo = service.read(bno);
		model.addAttribute("read",vo);
		model.addAttribute("scri",scri);
	}
	
	//글 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(Model model, int bno,@ModelAttribute("scri") SearchCriteria scri) {
		logger.info("get 수정");		
		System.out.println("들어옴?");
		System.out.println(bno);
		BoardVO vo = service.read(bno);	
		System.out.println("mdofiy"+vo);
		model.addAttribute("modify", vo);
		model.addAttribute("scri", scri);
	}
	
	//글수정post
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String postModify(BoardVO vo, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("post 수정");
		System.out.println(vo);
		service.update(vo);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		
		return "redirect:/board/listSearch";
	}
	
	//글삭제
//	@RequestMapping(value = "/delete",method = RequestMethod.GET)
//	public void getDelete(int bno, Model model) {
//		logger.info("get 삭제");
//		model.addAttribute("delete",bno);
//	}
	
	//글삭제 post 
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String postDelete(int bno,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("post delete");		
		service.delete(bno);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/listSearch";
	}
	
	
	
	
}
