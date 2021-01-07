package com.koreait.fashionshop.model.member.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberService {
	public List selectAll();//모든 회원가져오기
	public Member select(Member memebr);
	public void regist(Member member);
	public void update(Member member);
	public void delete(Member member);
}
