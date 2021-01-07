package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberDAO {
	public List selectAll();//모든 회원가져오기
	public Member select(Member Member);
	public void insert(Member member);
	public void update(Member member);
	public void delete(Member member);
	
}
