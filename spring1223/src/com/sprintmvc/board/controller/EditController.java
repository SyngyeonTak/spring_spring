package com.sprintmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class EditController implements Controller{
	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Board board = new Board();
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content= request.getParameter("content");
		
		board.setBoard_id(board_id);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = dao.update(board);
		
		ModelAndView mav = new ModelAndView();
		if(result==0) {
			mav.addObject("msg", "��������");
			mav.setViewName("error/result");
		}else {
			
			//mav.addObject("board", board);
			//��Ʈ�ѷ��� �� �� ���ļ� �� ���...
			mav.setViewName("redirect:/board/detail?board_id="+board_id);
			
			//������ jsp�� �����带 ���ϴ� ���
			//mav.setViewName("board/detail");
		}
		
		return mav;
	}

}
