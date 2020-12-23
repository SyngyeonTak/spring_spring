package com.sprintmvc.comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.comment.model.CommentDAO;
import com.model2.domain.Comment;

public class RegistController implements Controller{

	CommentDAO dao = new CommentDAO();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Comment comment = new Comment();
		String msg= request.getParameter("msg");
		String author= request.getParameter("author");
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(board_id);
		
		int result = dao.insert(comment);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		
		mav.setViewName("board/comment_regist");
		
		return mav;
	}

}





