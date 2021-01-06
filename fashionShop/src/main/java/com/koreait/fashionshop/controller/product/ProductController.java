package com.koreait.fashionshop.controller.product;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.common.FileManager;
import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.SubCategoryService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

/*관리자 모드에서의 상품에 대한 요청 처리*/

@Controller
public class ProductController implements ServletContextAware{
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileManager fileManager;
	
	//spring이 자동으로 부여해준다.
	private ServletContext servletContext;
	
	//HttpServletRequest없이 서블릿 컨텍스트를 얻어오는 방법->getRealPath()사용하려고!!
	
	public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
			//이 타이밍을 놓치지말고, 실제 물리적 경로를 FileManager에 대입해놓자!!!(saveBasic and saveAddon)
			fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
			fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
			logger.debug(fileManager.getSaveBasicDir());
	}
	
	//상위 카테고리 가져오기(관리자용)
	@RequestMapping(value="/admin/product/registform", method=RequestMethod.GET)
	public ModelAndView getTopList() {
		//3단계
		List topList = topCategoryService.selectAll();
		
		
		//4단계
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("admin/product/regist_form");
		return mav;
	}
	
	//하위 카테고리 가져오기
	//스프링에서는 자바객체와 Json간 변환을 자동으로 처리해주는 라이브러리를 지원한다.
	//vo를 json으로 자동 변환해주는 방법
	@RequestMapping(value="/admin/product/sublist", method=RequestMethod.GET)
	@ResponseBody
	public List getSubList(int topcategory_id) {
		List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
		return subList;
	}
	
	//jsp로 요청을 보내면 안된다.
	//String으로 보내되 반환값이 jsp가 아니라는 것을 알려줘야 한다. @ResponseBody
	//produces 옵션
	/*
	 * @ResponseBody//jsp와 같은 뷰페이지가 아닌, 단순 데이터만 전송
	 * 
	 * @RequestMapping(value="/admin/product/sublist", method=RequestMethod.GET,
	 * produces="application/json;charset=utf-8") public String getSubList(int
	 * topcategory_id) { logger.debug("전달 받은 topcategory_id: "+topcategory_id);
	 * List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
	 * StringBuilder sb = new StringBuilder(); sb.append("{");
	 * sb.append("\"subList\" : ["); for (int i = 0; i < subList.size(); i++) {
	 * SubCategory subCategory = subList.get(i); sb.append("{");
	 * sb.append("\"subcategory_id\":"+subCategory.getSubcategory_id()+",");
	 * sb.append("\"topcategory_id\":"+subCategory.getTopcategory_id()+",");
	 * sb.append("\"name\":\""+subCategory.getName()+"\"");
	 * 
	 * if(i < subList.size()-1) { sb.append("},"); }else { sb.append("}"); } }
	 *
		
		sb.append("]");
		sb.append("}");

		//리스트를 json으로 변형하여 보내줘야 함.
		return sb.toString();
	}
	*/
	
	//상품 목록
	@RequestMapping(value="/admin/product/list", method=RequestMethod.GET)
	public ModelAndView list() {
		List productList = productService.selectAll();
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		mav.addObject("productList", productList);
		
		return mav;
	}
	
	//상품 등록 폼
	@RequestMapping(value="/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}
	
	//상품 등록
	@RequestMapping(value="/admin/product/regist", method=RequestMethod.POST,
		produces="text/html;charset=utf-8")
	@ResponseBody
	public String registProduct(Product product) {
		logger.debug("하위 카테고리 "+product.getSubCategory().getSubcategory_id());
		logger.debug("상품명 "+product.getProduct_name());
		logger.debug("가격 "+product.getPrice());
		logger.debug("브랜드 "+product.getBrand());
		logger.debug("상세내용 "+product.getDetail());
		
		for (Psize psize : product.getPsize()) { 
			logger.debug(psize.getFit());
		}
		/*
		logger.debug("업로드 이미지명"+product.getRepImg().getOriginalFilename());
		
		for (int i = 0; i < product.getAddImg().length; i++) {
			
			logger.debug(product.getAddImg()[i].getOriginalFilename());
		}
		*/
		
		productService.regist(fileManager, product);//상품 등록
		
		//상품의 product_id를 이용하여, 대표이미지명을 결정 짓자
	
		
		
		//파일명이 생성되었으면, 이제 저장을 해보자!!
		//실제 물리적 경로를 얻어오려면, servletContext가 보유한 getRealPath()메서드가 필요하다.
		
		StringBuilder sb  = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"상품 등록 성공\"");

		sb.append("}");
		
		return sb.toString();
	}

	
	
	//상품 상세
	
	
	//상품 수정
	
	//상품 삭제
	
	//예외처리
	//위의 메서드 중에서 하나라도 예외가 발생하면, 아래의 핸들러가 동작
	@ExceptionHandler(ProductRegistException.class)
	@ResponseBody
	public String handleException(ProductRegistException e) {
		StringBuilder sb  = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":0,");
		sb.append("\"msg\":\""+e.getMessage()+"\"");

		sb.append("}");
		
		return sb.toString();
	}
	
	/*************************************************
	 	쇼핑몰 프론트 요청 처리
	 **************************************************/
	
	//상품목록 요청 처리
	@RequestMapping(value="/shop/product/list", method=RequestMethod.GET)
	public ModelAndView getShopProductList(int subcategory_id) {//하위카테고리의 id가 넘어간다.
		ModelAndView mav = new ModelAndView();
		List productList = productService.selectByAll(subcategory_id);
		List topList = topCategoryService.selectAll();
		mav.addObject("productList", productList);
		mav.addObject("topList", topList);
		mav.setViewName("shop/product/list");
		
		return mav;
	}
	
	@RequestMapping(value="/shop/product/detail", method=RequestMethod.GET)
	public ModelAndView getShopDetail(int product_id) {//하위카테고리의 id가 넘어간다.
		
		ModelAndView mav = new ModelAndView();
		List topList = topCategoryService.selectAll();
		Product product = productService.select(product_id);
		mav.addObject("topList", topList);
		mav.addObject("product", product);
		
		mav.setViewName("shop/product/detail");
		
		return mav;
	}
	
	
}














