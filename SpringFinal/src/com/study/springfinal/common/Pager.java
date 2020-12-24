/*
 paging 처리를 재사용성을 높인 클래스 정의 
*/
package com.study.springfinal.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	private int totalRecord;
	private int totalPage;
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num;

	private int pageSize = 10;
	private int currentPage=1;
	private int blockSize = 10;
	
	//선언된 변수 초기화
	public void init(HttpServletRequest request, List list) {
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		//페이지를 선택한 경우엔, 그 선택한 페이지로 대체
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage -(currentPage-1)%blockSize;
		lastPage = firstPage + (blockSize-1);
		curPos = (currentPage-1)*pageSize;
		num = totalRecord - curPos;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	
	
}









