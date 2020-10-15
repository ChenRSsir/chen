package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.BaseDict;
import com.turing.entity.CstCustomer;
import com.turing.entity.CstLost;
import com.turing.service.BaseDictService;
import com.turing.service.CstCustomerService;
import com.turing.service.CstLostService;
import com.turing.service.OrdersService;

/**
 * �ͻ����Ʋ�
 * @author Administrator
 *û�ı�״̬��Ҫ����
 */
@Controller
public class CstCustomerController {
	//�ͻ�����
	@Autowired
	private CstCustomerService cstCustomerService;
    //�����ֵ�����
	@Autowired
	private BaseDictService baseDictService;
	//����
	@Autowired
	private OrdersService ordersService;
	//��ʧ
	@Autowired
	private CstLostService cstLostService;
	//��ѯ 
	@RequestMapping("/selCustomer")
	public String selCustomer(){
		return "Customer/CustomerPage";
	}
	
	@RequestMapping("/selectCus")
	@ResponseBody
	public Object selectCus(Integer pageNum,Integer pageSize,String custNo,String custName,String custRegion,String custManagerName,Integer custLevel,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = request.getSession();
	    if(custNo==null){
	    	custNo="";
	    }
	    if(custName==null){
	    	custName="";
	    }
	    if(custRegion==null){
	    	custRegion="";
	    }
	    if(custManagerName==null){
	    	custManagerName="";
	    }
		//�ͻ��ȼ�
		List<BaseDict> levelList = baseDictService.findItem("1");
		//����
		List<BaseDict> regionList = baseDictService.findItem("3");
		//������
		session.setAttribute("levelList", levelList);
		session.setAttribute("regionList", regionList);
		//�ͻ���ʧ�ж�
		//��ѯ����״̬Ϊ�����Ŀͻ�
		List<CstCustomer> list = cstCustomerService.findCus();	
		String cust=null;
		//�������ϲ鿴�ͻ��������
		for (CstCustomer c : list) {
		
			//���ҿͻ��Ķ���
			cust = ordersService.findOrderTime(c.getCustName()); 
			if(cust!=null){
				//�޸Ŀͻ�״̬
				c.setCustStatus("2");
				int i = cstCustomerService.updateCustomer(c);
				if(i>0){
					//������ʧ�ͻ�
					CstLost cl=new CstLost();
					//�ͻ���Ϣ
					cl.setLstCustNo(c.getCustNo());
					cl.setLstCustName(c.getCustName());
					//�ͻ�����
					cl.setLstCustManagerId(c.getCustManagerId());
					cl.setLstCustManagerName(c.getCustManagerName());
					//�ϴ��µ�ʱ��
					String lstLastOrderDate = ordersService.findMaxOrderTime(c.getCustName());
					cl.setLstLastOrderDate(sdf.parse(lstLastOrderDate));
					//ȷ����ʧ
					cl.setLstLostDate(new Date());
					//״̬������
					cl.setLstStatus("1");
					//���
					cstLostService.addCusLost(cl);
				}
			}
		}
		//���÷�ҳ���
		PageHelper.startPage(pageNum, pageSize);
		//��ѯ���������ͻ�����
		List<CstCustomer> list2 = cstCustomerService.findCustomer(custNo,custName,custRegion,custManagerName,custLevel);
		PageInfo<CstCustomer> pageInfo=new PageInfo<>(list2);
		return pageInfo;
	}
	//��ѯһ��
	//Customer/CustomerEdit
	@RequestMapping("/findCusById/{custNo}")
	public ModelAndView findCusById(@PathVariable("custNo")String custNo){
		ModelAndView mv=new ModelAndView();
		CstCustomer customer = cstCustomerService.findCusById(custNo);
		mv.addObject("cus", customer);
		mv.setViewName("Customer/CustomerEdit");
		return mv;
	}
	
	//�޸�
	@RequestMapping("/updateCus")
	@ResponseBody
	public String updateCus(CstCustomer c){
		int i = cstCustomerService.updateCustomer(c);
		return i>0?"true":"false";
		
	}
	
	//ɾ������
	@RequestMapping("/deleteCus")
	@ResponseBody
	public String deleteCus(String cusId){
		CstCustomer customer=new CstCustomer();
		customer.setCustNo(cusId);
		customer.setCustStatus("3");
		int i = cstCustomerService.deleteCustomer(customer);
		return i>0?"true":"false";
	}
	//�ͻ�����
	@RequestMapping("/findCompose/{i}")
	public ModelAndView findCompose(@PathVariable("i")Integer i){
		ModelAndView mv=new ModelAndView();
		List<CstCustomer> list = cstCustomerService.findCompose(i);
		mv.addObject("list", list);
		mv.setViewName("Report/MakeReport");
		return mv;
	}
	
	//��ʧ�ͻ���ϵͳ�Զ��жϳ���6����û�µ��Ŀͻ��� ÿ�����賿����ɨ��ȫ��
	@Scheduled(cron="0 0 2 ? * SAT")
	public void findLostByTask() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("findLostByTask ִ���˰�");
		        //�ͻ���ʧ�ж�
				//��ѯ����״̬Ϊ�����Ŀͻ�
				List<CstCustomer> list = cstCustomerService.findCus();	
				String cust=null;
				//�������ϲ鿴�ͻ��������
				for (CstCustomer c : list) {
				
					//���ҿͻ��Ķ�������ȡ����6��û����Ŀͻ�����
					cust = ordersService.findOrderTime(c.getCustName()); 
					if(cust!=null){
						//�޸Ŀͻ�״̬
						c.setCustStatus("2");
						int i = cstCustomerService.updateCustomer(c);
						if(i>0){
							//������ʧ�ͻ�
							CstLost cl=new CstLost();
							//�ͻ���Ϣ
							cl.setLstCustNo(c.getCustNo());
							cl.setLstCustName(c.getCustName());
							//�ͻ�����
							cl.setLstCustManagerId(c.getCustManagerId());
							cl.setLstCustManagerName(c.getCustManagerName());
							//�ϴ��µ�ʱ��
							String lstLastOrderDate = ordersService.findMaxOrderTime(c.getCustName());
							cl.setLstLastOrderDate(sdf.parse(lstLastOrderDate));
							//ȷ����ʧ
							cl.setLstLostDate(new Date());
							//״̬������
							cl.setLstStatus("1");
							//���
							cstLostService.addCusLost(cl);
						}
					}
				}
	}
}
