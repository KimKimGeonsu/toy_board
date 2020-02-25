package com.test.geonsu.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.geonsu.dao.MemberDAO;
import com.test.geonsu.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO dao;

	//회원가입
	@Override
	public void register(MemberVO vo) {
		dao.register(vo);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return dao.login(vo);
	}

	@Override
	public void modify(MemberVO vo) {
		dao.modify(vo);
		
	}

	@Override
	public void delete(MemberVO vo) {
		dao.delete(vo);
		
	}

	@Override
	public MemberVO idCheck(String userId) {
		return dao.idCheck(userId);
	}
	
	

}
