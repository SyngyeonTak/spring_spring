package com.koreait.fashionshop.model.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.model.domain.TopCategory;
import com.koreait.fashionshop.model.product.repository.TopCategoryDAO;

@Service
public class TopCategroyServiceImpl implements TopCategoryService{

	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	public List selectAll() {
		return topCategoryDAO.selectAll();
	}

	public TopCategory select(int topcategory_id) {
		return null;
	}

	public void insert(TopCategory topcategory) {
		
	}

	public void update(TopCategory topcategory) {
		
	}

	public void delete(int topcategory_id) {
		
	}

}
