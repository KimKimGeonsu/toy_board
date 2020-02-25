package com.test.geonsu.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.geonsu.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	SqlSession sql;
	
	@Override
	public void register(MemberVO vo) {
		sql.insert("member.register", vo);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return sql.selectOne("member.login",vo);
	}

	@Override
	public void modify(MemberVO vo) {
		sql.update("member.modify",vo);
		
	}

	@Override
	public void delete(MemberVO vo) {
		sql.delete("member.delete",vo);
		
	}

	@Override
	public MemberVO idCheck(String userId) {		
		return sql.selectOne("member.idCheck",userId);
	}


	
	

}
