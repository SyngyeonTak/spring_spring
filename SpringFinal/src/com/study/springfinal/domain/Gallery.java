package com.study.springfinal.domain;

import org.springframework.web.multipart.MultipartFile;

public class Gallery {
	private int gallery_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private String filename;
	
	//파일 업로드 정보를 MultipartFile이라는 자료형에 담는다. 단, 맴버변수의 이름은 파라미터의 이름과 같아야 한다.
	private MultipartFile photo;
	
	
	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public int getGallery_id() {
		return gallery_id;
	}
	public void setGallery_id(int gallery_id) {
		this.gallery_id = gallery_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
}
