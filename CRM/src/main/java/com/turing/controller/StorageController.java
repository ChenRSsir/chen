package com.turing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.Storage;
import com.turing.service.StorageService;

/**
 * ��������
 * @author Administrator
 *
 */
@Controller
public class StorageController {
	
	@Autowired
	private StorageService storageService;
//��ѯ
	@RequestMapping("/findSto")
	public String findSto(){
		return "Manager/DepartSelect";
	}
	
	@RequestMapping("/findProStorage")
	@ResponseBody
	public Object findProStorage(Integer pageNum,Integer pageSize,String prodName,String stkWarehouse){
		//�жϴ��������Ƿ�Ϊ��
		if(prodName==null){
			prodName="";
		}
		if(stkWarehouse==null){
			stkWarehouse="";
		}
		//���÷�ҳ���
		PageHelper.startPage(pageNum, pageSize);
		List<Storage> list = storageService.selectProStorage(prodName, stkWarehouse);
		PageInfo<Storage> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}
}
