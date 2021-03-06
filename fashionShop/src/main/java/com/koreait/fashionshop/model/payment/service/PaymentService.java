package com.koreait.fashionshop.model.payment.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;

public interface PaymentService {
	//장바구니 관련 업무
	public List selectCartList();//회원구분없이 모든 내역 다 가져오기
	public List selectCartList(int member_id);
	public Cart selectCart(int cart_id);
	public void insert(Cart cart);
	public void update(List<Cart> cartList);//일괄 수정
	public void delete(Cart cart);//회원id에 속한 데이터 삭제할 예정
	public void delete(Member member);
	
	//결제 업무
	
	
}










