package com.test.geonsu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.geonsu.dao.BoardDAO;
import com.test.geonsu.domain.BoardVO;
import com.test.geonsu.domain.Criteria;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
	
	//작성
	@Override
	public void write(BoardVO vo) {
		dao.write(vo);		
	}
	
	//상세
	@Override
	public BoardVO read(int bno) {
		return dao.read(bno);
	}

	//수정
	@Override
	public void update(BoardVO vo) {
		 dao.update(vo);		
	}
	
	//삭제
	@Override
	public void delete(int bno) {
		dao.delete(bno);
		
	}

	//조회
	@Override
	public List<BoardVO> list(Criteria cri) {	
		return dao.list(cri);
	}

	//게시물갯수
	@Override
	public int listCount() {
		return dao.listCount();
	}
	

}
