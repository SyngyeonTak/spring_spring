package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Image;

@Repository
public class MybatisImageDAO implements ImageDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	
	public List selectAll() {
		return null;
	}

	public Image select(int image_id) {
		return null;
	}

	public List selectById(int product_id) {
		return null;
	}

	public void insert(Image image) {
		sessionTemplate.insert("Image.insert", image);
	}

	public void update(Image image) {
		
	}

	public void delete(int image_id) {
		
	}

}
