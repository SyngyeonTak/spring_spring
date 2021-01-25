package com.koreait.restproject.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.restproject.model.board.service.BoardService;
import com.koreait.restproject.model.domain.Board;

import lombok.extern.slf4j.Slf4j;

@RestController // RESTful rul을 이해한다. 또한 @ResponseBody가 이미 처리돼어 있다.
@Slf4j
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//목록 가져오기 요청
	@GetMapping("/board")//이미 ResponseBody가 적용된 상태이므로, 컨버터만 등록해놓았다면, List는 자동으로 Json으로 변환되어
										//클라이언트의 응답 정보로 사용됨...
	public List getList() {
		List boardList = boardService.selectAll();
		
		return boardList;
	}
	
	//한 건 가져오기 요청
	@GetMapping("/board/{board_id}")
	public Board getDetail(@PathVariable int board_id) {//@PathVariable는 {}에 오는 자리는 경로가 아닌 변수로 생각한다.
		log.debug("클라이언트가 보낸 파라미터는 "+board_id);
		
		return null;
	}
		
	
	//등록 요청
	@PostMapping("/board")
	//@RequestBody: 클라이언트가 전송한 json데이터를 자바의 객체로 변환(json --> java obj로 변환)
	public ResponseEntity<Board> regist(@RequestBody Board board) {
		log.debug("title is "+board.getTitle());
		log.debug("writer is "+board.getTitle());
		log.debug("content is "+board.getTitle());
		
		boardService.regist(board);
		
		return ResponseEntity.ok().body(board);//헤더뿐만 아니라 바디도 가져간다.(board_id가 이미 채워진 VO)
	}
	
	//수정 요청
	
	//삭제 요청
	
	
}












