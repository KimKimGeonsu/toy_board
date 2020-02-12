package com.test.geonsu.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.geonsu.dao.BoardDAO;
import com.test.geonsu.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
	
	//작성
	@Override
	public void write(BoardVO vo) {
		dao.write(vo);		
	}
	
	//조회
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
	

}
