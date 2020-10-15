package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.SalChance;
import com.turing.entity.SalPlan;
import com.turing.service.SalChanceService;
import com.turing.service.SalPlanMapperService;

/**
 * ���ۼƻ����Ʋ�
 * @author Administrator
 *
 */
@Controller
public class SalPlanMapperController {

	@Autowired
	private SalChanceService salChanceService;
	@Autowired
	private SalPlanMapperService salPlanMapperService;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * @param salMgerId ���ۻ���id
	 * @param i ��������������ж����ĸ���ѯ
	 * @return
	 */
	@RequestMapping("/findPlanById")
	public ModelAndView findPlanById(Integer salMgerId,Integer i){
		ModelAndView mv=new ModelAndView();
		//��ѯ���ۻ���
		SalChance salChance = salChanceService.findSalChanceById(salMgerId);
		//��ѯ���ۼƻ�
		List<SalPlan> planList = salPlanMapperService.findPlanList(salMgerId);
		mv.addObject("salChance", salChance);
		mv.addObject("planList", planList);
		//�鿴��Ϣ sale/LookPlay.html
		//�ƶ��ƻ� sale/SetPlay.html
	    //ִ�мƻ� sale/ExecPlay.html
		if(i==1){
		mv.setViewName("sale/LookPlay");
		}else if(i==2){
			mv.setViewName("sale/SetPlay");
		}else if(i==3){
			mv.setViewName("sale/ExecPlay");
		}
		return mv;
	}
	
	//���
	@RequestMapping("/addPlan")
	@ResponseBody
	public String addPlan(SalPlan plan,String pDate) throws Exception{
		plan.setPlaDate(sdf.parse(pDate));
		int i = salPlanMapperService.addPlan(plan);
		return i>0?"true":"false";
	}
	
	//�޸�
	@RequestMapping("/updatePlan")
	@ResponseBody
	public String updatePlan(SalPlan plan){
		int i = salPlanMapperService.updatePlan(plan);
		return i>0?"true":"false";
	}
	
	//ɾ��
	@RequestMapping("/deletePlan")
	@ResponseBody
	public String deletePlan(Integer plaId){
		int i = salPlanMapperService.deletePlan(plaId);
		return i>0?"true":"false";
	}
}
