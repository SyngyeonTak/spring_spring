package com.koreait.restproject.client.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.koreait.restproject.model.domain.Member;
import com.koreait.restproject.model.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//일반적인 요청을 처리하는 컨트롤러
@Controller
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;

	// jsp페이지를 반환하지 말고 데이터를 전송한다.
	@GetMapping("/member")
	public List<Member> getList() {
		log.debug("일반 리스트 요청했어??");
		List memberList = memberService.selectAll();
		return memberList;
	}

	@PostMapping("/member")
	public String regist(@RequestBody Member member) {// @RequestBody 클라이언트가 JSON이면 알아서 인스턴스에 매핑시켜줌

		log.debug("일반 등록을 원해?");
		log.debug("m_id: " + member.getM_id());
		log.debug("m_pass: " + member.getM_pass());
		log.debug("m_name: " + member.getM_name());

		memberService.regist(member);

		return "regist success";// REST에서는 개발자가 클라이언트에게 도대체 뭘 반환해야 할까?
	}

}
