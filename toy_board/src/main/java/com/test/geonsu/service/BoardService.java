package com.test.geonsu.service;

import com.test.geonsu.domain.BoardVO;

public interface BoardService {
	
	//작성
	public void write(BoardVO vo);
	
	//조회
	public BoardVO read(int bno);
	
	//수정
	public void update(BoardVO vo);
	
	//삭제
	public void delete(int bno);

}
