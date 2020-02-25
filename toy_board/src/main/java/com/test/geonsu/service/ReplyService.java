package com.test.geonsu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.geonsu.domain.ReplyVO;


@Service
public interface ReplyService {
	
	//댓글조회
	public List<ReplyVO> readReply(int bno);
	
	//댓글작성
	public void writeReply(ReplyVO vo);
	
	//특정댓글 조회
	public ReplyVO readReplySelect(int bno);
	
	//댓글수정
	public void replyUpdate(ReplyVO vo);
	
	//댓글삭제
	public void replyDelete(ReplyVO vo);
}
