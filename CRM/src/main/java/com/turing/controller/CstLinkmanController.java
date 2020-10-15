package com.turing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.CstCustomer;
import com.turing.entity.CstLinkman;
import com.turing.service.CstCustomerService;
import com.turing.service.CstLinkmanService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class CstLinkmanController {

	@Autowired
	private CstLinkmanService cstLinkmanService;
	@Autowired
	private CstCustomerService cstCustomerService;
	//��ѯ
	@RequestMapping("/findLinkMan/{custNo}")
	public ModelAndView findLinkMan(@PathVariable("custNo") String custNo,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		CstCustomer c = cstCustomerService.findCusById(custNo);
		List<CstLinkman> list = cstLinkmanService.findLinkManById(custNo);
		//������ͻ������� ������ʹ��
		request.getSession().setAttribute("c", c);
		mv.addObject("lmList", list);
		mv.setViewName("Customer/LinkManPage");
		return mv;
	}
	
	//����
	@RequestMapping("/addLink")
	public String addLink(){
		return "Customer/LinkManAdd";
	}
	
	@RequestMapping("/addLinkMan")
	@ResponseBody
	public String addLinkMan(CstLinkman linkman,HttpServletRequest request){
		//���ͻ�id ����ȡ��,������ϵ����Ϣ��
		CstCustomer c =(CstCustomer)request.getSession().getAttribute("c");
		linkman.setLkmCustNo(c.getCustNo());
		linkman.setLkmCustName(c.getCustName());
		int i = cstLinkmanService.addLinkMan(linkman);
		return i>0?"true":"false";
	}
	
	
	//��ѯһ��
	@RequestMapping("/findlmById/{lkmId}")
	public ModelAndView findlmById(@PathVariable("lkmId")Integer lkmId){
		ModelAndView mv=new ModelAndView();
		CstLinkman linkman = cstLinkmanService.findlmById(lkmId);
		mv.addObject("lm", linkman);
		mv.setViewName("Customer/LinkManEdit");
		return mv;
	}
	
	//�޸�
	@RequestMapping("/updateLm")
	@ResponseBody
	public String updateLm(CstLinkman linkman){
		int i = cstLinkmanService.updateLinkMan(linkman);
		return i>0?"true":"false";
	}
	
	
	//ɾ��
	@RequestMapping("/deletelm")
	public ModelAndView deletelm(Integer lid,HttpServletRequest request){
		CstCustomer c =(CstCustomer)request.getSession().getAttribute("c");
		ModelAndView mv=new ModelAndView();
		int i = cstLinkmanService.deleteLinkMan(lid);
		if(i>0){
			mv = findLinkMan(c.getCustNo(), request);
		}
		return mv;
	}
}
