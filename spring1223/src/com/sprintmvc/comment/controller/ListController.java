package com.sprintmvc.comment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.comment.model.CommentDAO;

public class ListController implements Controller{
	CommentDAO dao = new CommentDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		List commentList = dao.selectAll(board_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("commentList", commentList);
		mav.setViewName("board/comment_list");
		
		return mav;
	}

}











