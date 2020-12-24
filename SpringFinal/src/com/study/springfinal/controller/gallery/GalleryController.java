package com.study.springfinal.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.study.springfinal.common.FileManager;
import com.study.springfinal.domain.Gallery;
import com.study.springfinal.model.dao.GalleryDAO;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	//ǥ�ø� ���״�, ���⿡ �־��ּ����� �����Ѵ�.
	//AutoWired: �˾Ƽ� ������ �ȴ�.
	@Autowired
	private GalleryDAO galleryDAO;

	//������ �����ӿ�ũ�� ���ε� ������Ʈ�� apache, fileupload�� �����
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(Gallery gallery, HttpServletRequest request) {
		
		//������ ����
		MultipartFile photo = gallery.getPhoto();
		System.out.println("originalFilename is "+photo.getOriginalFilename());
		System.out.println("getName is "+photo.getName());
		System.out.println("getSize is "+photo.getSize());
		System.out.println("ContentType is "+photo.getContentType());
		
		//���ϸ� ���� �����, �����ϱ�
		String newName = Long.toString(System.currentTimeMillis());
		String ext = FileManager.getExtend(photo.getOriginalFilename());
		String filename = newName+"."+ext;//�������ϸ�
		gallery.setFilename(filename);
		ServletContext context = request.getServletContext();
		String saveDir = context.getRealPath("/data");
		//��Ŭ���� ���� ������ ���, ���� ��οʹ� �ٸ� ��ο� ����... ���߽ÿ� �� ��θ� Ȯ���Ϸ���
		//����غ���
		System.out.println(saveDir);
		try {
			photo.transferTo(new File(saveDir+"/"+filename));//dir���
			int result = galleryDAO.insert(gallery);
			System.out.println("��ϰ���� "+result);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/gallery/list";
	}
	
	//��� ��������
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		//3�ܰ�
		List galleryList = galleryDAO.selectAll();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("galleryList", galleryList);
		mav.setViewName("gallery/list");
		
		return mav;
	}
	
	//�󼼺��� ��û ó��
	//������ �Ȱ��ٸ� �׳� �־��ָ��
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView select(int gallery_id) {
		
		Gallery gallery = galleryDAO.select(gallery_id);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("gallery", gallery);
		mav.setViewName("gallery/detail");
		
		return mav;
	}
	
	//�� ���� ��û ó�� 
		@RequestMapping(value="/edit", method=RequestMethod.POST)
		public String edit(Gallery gallery) {
			//3�ܰ�:�Ͻ�Ű��
			galleryDAO.update(gallery);
			
			//4�ܰ�:������ ���� ����. �� ?? ��û�� ���� detail�� ���� �����ҰŴϱ�..
			return "redirect:/gallery/detail?gallery_id="+gallery.getGallery_id();
		}
		
		//�� ���� ��û ó��
		@RequestMapping(value="/delete", method=RequestMethod.POST)
		public String delete(int gallery_id) {
			//3�ܰ�: �� ��Ű�� 
			galleryDAO.delete(gallery_id);
			
			//4�ܰ�: �����Ұ��� ����. ��? list�� ���Ӱ� �����ҰŴϱ�..
			return "redirect:/gallery/list";
		}
	
}













