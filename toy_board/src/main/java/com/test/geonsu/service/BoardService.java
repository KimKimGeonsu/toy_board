package com.test.geonsu.service;

import java.util.List;

import com.test.geonsu.domain.BoardVO;
import com.test.geonsu.domain.Criteria;
import com.test.geonsu.domain.SearchCriteria;

public interface BoardService {
	
	//작성
	public void write(BoardVO vo);
	
	//상세
	public BoardVO read(int bno);
	
	//수정
	public void update(BoardVO vo);
	
	//삭제
	public void delete(int bno);
	
	//조회
	public List<BoardVO> list(Criteria cri);
	
	//게시물갯수
	public int listCount();
	
	//검색
	public List<BoardVO> listSearch(SearchCriteria scri);
	
	//검색 결과갯수
	public int countSearch(SearchCriteria scri);

}
