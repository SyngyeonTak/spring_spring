package com.koreait.fashionshop.controller.payment;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.fashionshop.common.MessageData;
import com.koreait.fashionshop.exception.CartException;
import com.koreait.fashionshop.exception.LoginRequiredException;
import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.payment.service.PaymentService;

/*비동기인 녀석들만 담는? controller
 * 휴대폰은 중립적인 data(json, xml)만을 다루기 때문에....
 * 컨트롤러를 왜 나눔? 유지보수 때문에*/
@Controller
@RequestMapping(value="/async")
public class RestPaymentController {
	private static final Logger logger = LoggerFactory.getLogger(RestPaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	//장바구니에 상품 담기 요청
	@RequestMapping(value="/shop/cart/regist", method=RequestMethod.POST)
	@ResponseBody
	public MessageData registCart(Cart cart, HttpSession session) {
		if(session.getAttribute("member")==null) {
			//여기서 예외를 처리하면, 모든 컨트롤러 메서드마다 로그인과 관련된 코드가 중복되므로,
			//예외를 일으켜 하나의 메서드에서 처리하도록 재사용성을 높이자.
			throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
		}
		
		Member member = (Member)session.getAttribute("member");
		logger.debug("product_id "+cart.getProduct_id());
		logger.debug("quantity "+cart.getQuantity());
		cart.setMember_id(member.getMember_id());
		
		paymentService.insert(cart);
		
		//MessageConverter에 의해 VO는 JSON형태로 응답되어질 수 있다!!
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("장바구니에 상품이 담겼습니다.");
		messageData.setUrl("/shop/cart/list");
		
		return messageData;
	}
	
	
	//장바구니와 관련된 예외처리 핸들러
	@ExceptionHandler(CartException.class)
	@ResponseBody
	public MessageData handleException(CartException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		return messageData ;
	}
	
	@ExceptionHandler(LoginRequiredException.class)
	@ResponseBody
	public MessageData handleException(LoginRequiredException e) {
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		return messageData;
	}
	
	
	
}
















