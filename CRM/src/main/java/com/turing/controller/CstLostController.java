package com.turing.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.CstLost;
import com.turing.service.CstLostService;

/**
 * �ͻ���ʧ���Ʋ�
 * @author Administrator
 *
 */
@Controller
public class CstLostController {

	@Autowired
	private CstLostService cstLostService;
	
	@RequestMapping("/findCl")
	//Customer/LostsPage.html
	public String findCl(){
		return "Customer/LostsPage";
	}
	//ģ����ѯ
	@RequestMapping("/findCusLost")
	@ResponseBody
	public Object findCusLost(Integer pageNum,Integer pageSize,String lstCustName, String lstCustManagerName, String lstStatus){

		if(lstCustName==null){
			lstCustName="";
		}
		if(lstCustManagerName==null){
			lstCustManagerName="";
		}
		if(lstStatus==null){
			//����
			lstStatus="";
		}
		PageHelper.startPage(pageNum, pageSize);
		List<CstLost> list = cstLostService.findCusLost(lstCustName, lstCustManagerName, lstStatus);
	    PageInfo<CstLost> pageInfo=new PageInfo<>(list);
	    return pageInfo;
	}
	
	//��ʩ ��ѯ
	//Customer/LostPause �ݻ�
	//Customer/LostEnter ��ʧ
	//Customer/LostLook �鿴
	@RequestMapping("/lstdelay")
	public ModelAndView lstdelay(Integer lid,Integer i){
		ModelAndView mv=new ModelAndView();
		CstLost cstLost = cstLostService.findClostById(lid);
		mv.addObject("cstLost", cstLost);
		if(i==1){
		   mv.setViewName("Customer/LostPause");
		}else if(i==2){
		   mv.setViewName("Customer/LostEnter");
		}else if(i==3){
			 mv.setViewName("Customer/LostLook");
		}
		return mv;
	}
	
	//�޸�
	@RequestMapping("/updateLost")
	@ResponseBody
	public String updateLost(CstLost cstLost){
		int i = cstLostService.updateCusLost(cstLost);
		return i>0?"true":"false";
	}
	
	//��ʧ��Ϣ����
	@RequestMapping("/findLost")
	public ModelAndView findLost(String lstCustName, String lstCustManagerName){
		ModelAndView mv=new ModelAndView();
		if(lstCustName==null){
			lstCustName="";
		}
		if(lstCustManagerName==null){
			lstCustManagerName="";
		}
		List<CstLost> lostList = cstLostService.findLost(lstCustName, lstCustManagerName);
		mv.addObject("lostList", lostList);
		mv.setViewName("Report/LostReport");
		return mv;
	}
}
