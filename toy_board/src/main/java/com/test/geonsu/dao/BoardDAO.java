package com.test.geonsu.dao;

import java.util.List;

import com.test.geonsu.domain.BoardVO;

public interface BoardDAO {
	
	//작성
	public void write(BoardVO vo);
	
	//상세
	public BoardVO read(int bno);
	
	//수정
	public void update(BoardVO vo);
	
	//삭제
	public void delete(int bno);
	
	//조회
	public List<BoardVO> list();

}
