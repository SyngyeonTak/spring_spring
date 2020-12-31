package com.koreait.fashionshop.model.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.TopCategory;

@Service
public interface ProductService {
	//CRUD
		public List selectAll();
		public List selectByAll(int subcategory_id);
		public Product select(int product_id);
		public void regist(Product product);
		public void update(Product product);
		public void delete(int product_id);
}
