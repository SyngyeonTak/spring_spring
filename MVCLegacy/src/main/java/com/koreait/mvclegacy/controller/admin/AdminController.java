package com.koreait.mvclegacy.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvclegacy.model.member.MemberService;

/*
 * 관리자 기능과 관련된 모든 요청을 처리하는 컨트롤러
 * */

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String test() {
		//All< DEBUG < INFO < WARN < ERROR < FATAL < OFF
		logger.debug("test() 호출");
		logger.debug("어드민에서 사용중인 MemberService의 주솟값 "+memberService);
		memberService.regist();
		return null;
	}
}










