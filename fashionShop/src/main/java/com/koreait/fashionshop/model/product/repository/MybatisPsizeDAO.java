package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.model.domain.Psize;

@Repository
public class MybatisPsizeDAO implements PsizeDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAllById(int product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Psize select(int psize_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Psize psize) throws ProductRegistException{
		int result = sessionTemplate.insert("Psize.insert", psize);
		if(result == 0) {
			throw new ProductRegistException("psize 테이블에 입력 실패");
		}
	}

	@Override
	public void delete(int psize_id) throws ProductRegistException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updata(Psize psize) throws ProductRegistException{
		// TODO Auto-generated method stub
		
	}

}
