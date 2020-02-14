package com.test.geonsu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.geonsu.domain.BoardVO;
import com.test.geonsu.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	SqlSession sql;
	
	//작성
	@Override
	public void write(BoardVO vo) {
		sql.insert("board.write",vo);		
	}
	
	//상세
	@Override
	public BoardVO read(int bno) {
		return sql.selectOne("board.read",bno);
	}
	
	//수정
	@Override
	public void update(BoardVO vo) {		
		sql.update("board.update",vo);
	}

	//삭제
	@Override
	public void delete(int bno) {
		sql.delete("board.delete",bno);		
	}

	//리스트
	@Override
	public List<BoardVO> list(Criteria cri) {
		return sql.selectList("board.listpage",cri);
	}

	//게시물 총갯수
	@Override
	public int listCount() { 
		return sql.selectOne("board.listCount");
	}

	
	
}
