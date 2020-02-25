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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.geonsu.domain.BoardVO;
import com.test.geonsu.domain.PageMaker;
import com.test.geonsu.domain.ReplyVO;
import com.test.geonsu.domain.SearchCriteria;
import com.test.geonsu.service.BoardService;
import com.test.geonsu.service.ReplyService;


@Controller
@RequestMapping("/board/*")
public class BoardController {	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	@Inject
	ReplyService Re_service;
	
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
		
		//댓글부분
		List<ReplyVO> re_list = Re_service.readReply(bno);
		System.out.println("댓글부분"+re_list);
		model.addAttribute("re_list",re_list);
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
	
	//댓글 작성
	@RequestMapping(value = "/replyWrite",method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("post 댓글");
		
		Re_service.writeReply(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());				
		
		return "redirect:/board/read";
	}
	
	//댓글 수정
	@RequestMapping(value = "/replyUpdate",method = RequestMethod.POST)
	public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("댓글수정");
		Re_service.replyUpdate(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());				
		
		return "redirect:/board/read";
		
	}
	
	//댓글삭제
	@RequestMapping(value = "/replyDelete",method = RequestMethod.POST)
	public String replyDelete(ReplyVO vo,SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("댓글삭제");
		Re_service.replyDelete(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());				
		
		return "redirect:/board/read";
	}
	
	//댓글수정get
	@RequestMapping(value = "/replyUpdate")
	public void getReplyUpdate(@RequestParam("rno") int rno, @ModelAttribute("scri") SearchCriteria scri, Model model) {	 
		ReplyVO vo =  Re_service.readReplySelect(rno);
		model.addAttribute("readReply",vo );
		model.addAttribute("scri", scri);
		
	}
	
	//댓글삭제 get
	@RequestMapping(value = "/replyDelete")
	public void getReplyDelete(@RequestParam("rno") int rno, @ModelAttribute("scri") SearchCriteria scri, Model model) {	 
		ReplyVO vo =  Re_service.readReplySelect(rno);
		model.addAttribute("readReply",vo );
		model.addAttribute("scri", scri);
		
	}
	
	
	
	
}
