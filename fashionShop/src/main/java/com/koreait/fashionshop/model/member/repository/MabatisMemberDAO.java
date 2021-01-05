package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;

@Repository
public class MabatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insert(Member member) throws MemberRegistException{
		int result = sqlsessionTemplate.insert("Member.insert", member);
		if(result == 0) {
			throw new MemberRegistException("회원가입에 실패하였습니다.");
		}
	}

	@Override
	public void update(Member member) throws MemberRegistException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) throws MemberRegistException{
		// TODO Auto-generated method stub
		
	}

}
