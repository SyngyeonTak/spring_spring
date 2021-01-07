package com.koreait.fashionshop.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.common.MailSender;
import com.koreait.fashionshop.common.SecureManager;
import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberNotFoundException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	//이메일 발송 객체 추가
	@Autowired
	private MailSender mailSender;//원래는 인터페이스로 가야됨...구글, 네이버 이렇게 나뉠 수 있기 때문에
	
	@Autowired
	private SecureManager secureManager;
	
	
	public List selectAll() {
		return null;
	}

	//암호화 객체
	
	public Member select(Member member) throws MemberNotFoundException{
		//유저가 전송한 파라미터 비밀번호를 해시값으로 변환하여 아래의 메서드 호출
		String secureData = secureManager.getSecureData(member.getPassword());
		member.setPassword(secureData);//VO에 해시값 대입!!
		
		return memberDAO.select(member);
	}

	@Override
	public void regist(Member member) throws MemberRegistException, MailSendException{
		//DB에 넣기 + 이메일 보내기 + 문자발송
		
		//비밀번호 암호화 하기
		String secureData = secureManager.getSecureData(member.getPassword());
		member.setPassword(secureData);
		
		//db에 insert
		memberDAO.insert(member);
		
		//이메일 보내기
		String name = member.getName();
		String addr = member.getAddr();
		String email = member.getEmail_id()+"@"+member.getEmail_server();
		mailSender.send(email, name+"님 [패션샵]회원가입", addr+"에 거주하세요??");
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}

}
