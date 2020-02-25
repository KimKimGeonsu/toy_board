package com.test.geonsu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.geonsu.domain.ReplyVO;
@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Inject
	SqlSession sql;

	//댓글조회
	@Override
	public List<ReplyVO> readReply(int bno) {
		return sql.selectList("board.readReply", bno);
	}
	//댓글작성
	@Override
	public void writeRely(ReplyVO vo) {
		sql.insert("board.writeReply",vo);		
	}
	//특정댓글조회
	@Override
	public ReplyVO readReplySelect(int bno) {
		return sql.selectOne("board.readReplySelect",bno);
	}
	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO vo) {
		sql.update("board.updateReply",vo);
		
	}
	//댓글 삭제
	@Override
	public void replyDelete(ReplyVO vo) {
		sql.delete("board.deleteReply",vo);
		
	}

}
