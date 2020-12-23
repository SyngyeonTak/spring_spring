package com.sprintmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	//DI�� �ܺο��� ��ü�� �ν��Ͻ��� ���Թ޴� ���(������ ��������, setter�� �����ڸ� �غ��ؾ� ��)
	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List boardList = dao.selectAll();
		System.out.println("�Խù� ��:"+boardList.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("board/list");
		return mav;
	}

}
