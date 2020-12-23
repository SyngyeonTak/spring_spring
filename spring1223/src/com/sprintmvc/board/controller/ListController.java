package com.sprintmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	//DI란 외부에서 객체인 인스턴스를 주입받는 방법(주입을 받으려면, setter나 생성자를 준비해야 함)
	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List boardList = dao.selectAll();
		System.out.println("게시물 수:"+boardList.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("board/list");
		return mav;
	}

}
