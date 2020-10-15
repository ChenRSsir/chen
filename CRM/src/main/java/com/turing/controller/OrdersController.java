package com.turing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.CstCustomer;
import com.turing.entity.Orders;
import com.turing.service.CstCustomerService;
import com.turing.service.OrdersService;

/**
 * �������Ʋ�
 * @author Administrator
 *
 */
@Controller
public class OrdersController {

	@Autowired
	private CstCustomerService cstCustomerService;
	@Autowired
	private OrdersService ordersService;
	//��ѯ
	//Customer/OrderPage.html
	@RequestMapping("/findOrdByCus")
	public ModelAndView findOrdByCus(String custNo,Integer pageNum,Integer pageSize,HttpServletRequest request){
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=3;
		}
		ModelAndView mv=new ModelAndView();
		//��ȡ��ǰ�ͻ�
		CstCustomer customer = cstCustomerService.findCusById(custNo);
		//ͨ���û����ƻ�ȡ����
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> Orderlist = ordersService.findOrderByCus(customer.getCustName());
		PageInfo<Orders> pageInfo=new PageInfo<>(Orderlist);
		//���ͻ� �Ͷ���������
		mv.addObject("c", customer);
		mv.addObject("Orderlist", pageInfo);
		mv.setViewName("Customer/OrderPage");
		return mv;
	}
	 
	//��ѯ�������׷���
	@RequestMapping("/findOrderTotal")
	public ModelAndView findOrderTotal(String customer,String dateTime){
		if(customer==null){
			customer="";
		}
		if(dateTime==null || dateTime==""){
			dateTime=null;
		}
		ModelAndView mv=new ModelAndView();
		List<Orders> total = ordersService.findOrderTotal(customer, dateTime);
		mv.addObject("total", total);
		mv.setViewName("Report/ContributeReport");
		return mv;
	}
}
