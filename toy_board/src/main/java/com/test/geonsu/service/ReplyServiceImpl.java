package com.test.geonsu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.geonsu.dao.ReplyDAO;
import com.test.geonsu.domain.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Inject
	private ReplyDAO dao;
	
	//댓글조회
	@Override
	public List<ReplyVO> readReply(int bno) { 
		return dao.readReply(bno);
	}
	
	//댓글 작성
	public void writeReply(ReplyVO vo) {
		dao.writeRely(vo);
	}
	//특정댓글
	@Override
	public ReplyVO readReplySelect(int bno) {
		return dao.readReplySelect(bno);
	}
	//댓글수정
	@Override
	public void replyUpdate(ReplyVO vo) {
		dao.replyUpdate(vo);
		
	}
	//댓글삭제
	@Override
	public void replyDelete(ReplyVO vo) {
		dao.replyDelete(vo);
		
	}

}
