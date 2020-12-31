package com.koreait.fashionshop.model.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.fashionshop.model.domain.SubCategory;

@Service
public interface SubCategoryService {
	//CRUD
	public List selectAll();//모든 레코드 가져오기 
	public List selectAllById(int topcategory_id);//t선택한 상위 카테고리에 소속된 하위카테고리 목록 가져오기 
	public SubCategory select(int subcategory_id);
	public void insert(SubCategory subcategory);
	public void update(SubCategory subcategory);
	public void delete(int topcategory_id);
}
