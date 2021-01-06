package com.koreait.fashionshop.exception;

//CRUD 작업시 발생되는 예외
public class CartException extends RuntimeException{
	public CartException(String msg) {
		super(msg);
	}
	public CartException(String msg, Throwable e) {
		super(msg, e);
	}
}
