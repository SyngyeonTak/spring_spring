package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.SubCategory;

@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public List selectAll() {
		return null;
	}

	public List selectAllById(int topcategory_id) {
		return sqlSessionTemplate.selectList("SubCategory.selectAllById", topcategory_id);
	}

	public SubCategory select(int subcategory_id) {
		return null;
	}

	public void insert(SubCategory subcategory) {
		
	}

	public void update(SubCategory subcategory) {
		
	}

	public void delete(int topcategory_id) {
		
	}

}
