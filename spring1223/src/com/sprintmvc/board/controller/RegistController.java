package com.sprintmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class RegistController implements Controller{
	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Board board = new Board();
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		dao.insert(board);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list");
		
		return mav;
	}

}
