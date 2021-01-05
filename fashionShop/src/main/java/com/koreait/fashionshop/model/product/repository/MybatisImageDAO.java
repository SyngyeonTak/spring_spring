package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.ProductRegistException;
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

	public void insert(Image image) throws ProductRegistException{
		int result = sessionTemplate.insert("Image.insert", image);
		if(result==0) {
			throw new ProductRegistException("image 테이블에 입력 실패");
		}
	}

	public void update(Image image) throws ProductRegistException{
		
	}

	public void delete(int image_id) throws ProductRegistException{
		
	}

}
