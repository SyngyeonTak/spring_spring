package com.sprintmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class DeleteController implements Controller{
	private BoardDAO dao;	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		int result = dao.delete(board_id);
		
		ModelAndView mav = new ModelAndView();
		if(result==0) {
			mav.addObject("msg", "삭제실패");
			mav.setViewName("error/result");//실패한 경우의 url
		}else {
			mav.setViewName("redirect:/board/list");//성공한 경우의 url			
		}
		
		return mav;
	}

}











