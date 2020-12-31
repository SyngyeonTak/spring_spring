package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.TopCategory;

@Repository
public interface ProductDAO {
	//CRUD
	public List selectAll();
	public List selectByAll(int subcategory_id);
	public Product select(int product_id);
	public void insert(Product product);
	public void update(Product product);
	public void delete(int product_id);
}
