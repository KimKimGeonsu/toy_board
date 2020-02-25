package com.test.geonsu.service;

import com.test.geonsu.domain.MemberVO;

public interface MemberService {
	
	//회원가입
	public void register(MemberVO vo);
	//로그인
	public MemberVO login(MemberVO vo);
	
	//회원정보 수정
	public void modify(MemberVO vo);
	
	//회원탈퇴
	public void delete(MemberVO vo);
	
	//아이디확인
	public MemberVO idCheck(String userId);

}
