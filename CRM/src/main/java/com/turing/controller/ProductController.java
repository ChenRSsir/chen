package com.turing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.Product;
import com.turing.service.ProductService;

/**
 * ��Ʒ��Ϣ������
 * @author Administrator
 *
 */
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;

	//��Main����
	@RequestMapping("/findPro1")
	public String findPro1(){
		return "Manager/ProductSelect";
	}
	
	//��ѯ
	@RequestMapping("/findPro")
	@ResponseBody
	public Object findPro(Integer pageNum,Integer pageSize, String prodName, String prodType, String prodBatch,ModelAndView mv){
		
		//�жϴ���ҳ���Ƿ�Ϊ��
		if(prodName==null){
			prodName="";
		}
		if(prodType==null){
			prodType="";
		}
		if(prodBatch==null){
			prodBatch="";
		}
		//���÷�ҳ���
		PageHelper.startPage(pageNum, pageSize);
	    List<Product> list = productService.findPro(prodName, prodType, prodBatch);

		PageInfo<Product> pageInfo=new PageInfo<>(list);
		
		return pageInfo;
	}
}
