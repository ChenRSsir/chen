package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.CstActivity;
import com.turing.entity.CstCustomer;
import com.turing.service.CstActivityService;
import com.turing.service.CstCustomerService;

/**
 * ����Ʋ�
 * @author Administrator
 *
 */
@Controller
public class CstActivityController {

	@Autowired
	private CstActivityService cstActivityService;
	@Autowired
	private CstCustomerService cstCustomerService;
	
	//ͨ���ͻ�id��ѯ���Ϣ
	@RequestMapping("/findActList/{custNo}")
	//Customer/ActivitysPage
	public ModelAndView findActList(@PathVariable("custNo") String custNo,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		CstCustomer c = cstCustomerService.findCusById(custNo);
		List<CstActivity> actList = cstActivityService.findActList(custNo);
		//���ͻ�������
		request.getSession().setAttribute("c", c);
		mv.addObject("actList", actList);
		mv.setViewName("Customer/ActivitysPage");
		return mv;
	}
	
	//����
	@RequestMapping("/addAtv")
	public String addAtv(){
	  return "Customer/ActivitysAdd";
	}
	
	@RequestMapping("/addAtvty")
	@ResponseBody
	public String addAtvty(CstActivity activity,String atvDates,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//���ͻ�id ����ȡ��,������ϵ����Ϣ��
	   CstCustomer c =(CstCustomer)request.getSession().getAttribute("c");
	   activity.setAtvCustNo(c.getCustNo());
	   activity.setAtvCustName(c.getCustName());
	   activity.setAtvDate(sdf.parse(atvDates));
	   int i = cstActivityService.addAct(activity);
	   return i>0?"true":"false";
	}
	
	//��ѯһ����¼
	//Customer/ActivitysEdit
	@RequestMapping("findAtvById/{atvId}")
	public ModelAndView findAtvById(@PathVariable("atvId")Integer atvId){
		ModelAndView mv=new ModelAndView();
		CstActivity activity = cstActivityService.findActById(atvId);
		mv.addObject("activity", activity);
		mv.setViewName("Customer/ActivitysEdit");
		return mv;
	}
	
	//�޸�
	@RequestMapping("/updateActv")
	@ResponseBody
	public String updateActv(CstActivity activity,String atvDates) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		activity.setAtvDate(sdf.parse(atvDates));
		int i = cstActivityService.updateAct(activity);
		return i>0?"true":"false";
	}
	
	//ɾ��
	@RequestMapping("/deleteAtv")
	public ModelAndView deleteAtv(Integer atvid,HttpServletRequest request){
		CstCustomer c =(CstCustomer)request.getSession().getAttribute("c");
		ModelAndView mv=new ModelAndView();
		int i = cstActivityService.deleteAct(atvid);
		if(i>0){
			mv=findActList(c.getCustNo(),request);
		}
		return mv;
	}
}
