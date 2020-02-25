package com.test.geonsu.dao;

import java.util.List;

import com.test.geonsu.domain.ReplyVO;

public interface ReplyDAO {

	//댓글조회
	public List<ReplyVO> readReply(int bno);
	
	//댓글작성
	public void writeRely(ReplyVO vo);
	
	//특정 댓글조회
	public ReplyVO readReplySelect(int bno);
	
	//댓글수정
	public void replyUpdate(ReplyVO vo);

	//댓글삭제
	public void replyDelete(ReplyVO vo);
	
}
