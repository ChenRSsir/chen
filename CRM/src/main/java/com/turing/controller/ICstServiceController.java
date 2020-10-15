package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.BaseDict;
import com.turing.entity.CstCustomer;
import com.turing.entity.CstService;
import com.turing.entity.SysUser;
import com.turing.service.BaseDictService;
import com.turing.service.CstCustomerService;
import com.turing.service.ICstService;
import com.turing.service.SysUserService;

/**
 * �ͻ�������Ʋ�
 * @author Administrator
 *
 */
@Controller
public class ICstServiceController {
    //�ͻ�����
	@Autowired
	private ICstService iCstService;
	//�����ֵ�
	@Autowired
	private BaseDictService baseDictService;
	//�ͻ�
	@Autowired
	private CstCustomerService cstCustomerService;
	//�û�
	@Autowired
	private SysUserService sysUserService;
	
	//����
	@RequestMapping("/createCstService")
	public ModelAndView createCstService(){
		ModelAndView mv=new ModelAndView();
		//�����ֵ䣬��������
		List<BaseDict> item = baseDictService.findItem("2");
		mv.addObject("item", item);
		mv.setViewName("CustomerService/ServiceCreate");
		return mv;
	}
	
	@RequestMapping("/addCstService")
	@ResponseBody
	public String addCstService(CstService cs,HttpServletRequest r){
		//�ж�����Ŀͻ��Ƿ������пͻ�
		CstCustomer customer = cstCustomerService.findByName(cs.getSvrCustName());
		if(customer!=null){
			//�ͻ����
			cs.setSvrCustNo(customer.getCustNo());
		}
		//״̬1 ������
		cs.setSvrStatus("1");
		//������
		SysUser user =(SysUser)r.getSession().getAttribute("user");
		cs.setSvrCreateId(user.getUserId());
		cs.setSvrCreateBy(user.getUserName());
		//����ʱ�� ��ǰʱ��
		cs.setSvrCreateDate(new Date());
		
		int i = iCstService.addCstService(cs);
			
		return i>0?"true":"false";
	}
	
	//���� ��ѯ
	@RequestMapping("/findCs")
	public String findCs(){
		return "CustomerService/ServiceAllot";
	}
	//���� ��ѯ
	@RequestMapping("/svrDeal")
	public String svrDeal(){
		return "CustomerService/ServiceDispose";
	}
	//������ѯ
	@RequestMapping("/Sresult")
	public String Sresult(){
		return "CustomerService/ServiceResult";
	}
	 
	//�鵵
	@RequestMapping("/archives")
	public String archives(){
		return "CustomerService/ServiceDetail";
	}
	
	//���Ӳ�ѯ
	@RequestMapping("/findCusService")
	@ResponseBody
	public Object[] findCusService(Integer pageNum,Integer pageSize, CstService cs,String bDate1, String bDate2,HttpServletRequest r) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = r.getSession();
		Date date=null;
		if(cs.getSvrCustName()==null){
			cs.setSvrCustName("");
		}
		if(cs.getSvrTitle()==null){
			cs.setSvrTitle("");
		}
		if(cs.getSvrType()==null){
			cs.setSvrType("");
		}
		if(cs.getSvrStatus()==null){
			cs.setSvrStatus("");
		}
		if(bDate1!=null && bDate2!=null && bDate1!="" && bDate2!=""){
			cs.setSvrCreateDate(sdf.parse(bDate1));
			 date = sdf.parse(bDate2);
		}
		//��ȡ�û�
		List<SysUser> userlist = sysUserService.getUsers();
		
		//�����ֵ䣬��������
		List<BaseDict> itemlist = baseDictService.findItem("2");
		session.setAttribute("itemlist", itemlist);
		//��ҳ���
		PageHelper.startPage(pageNum, pageSize);
		List<CstService> cstService = iCstService.findCstService(cs, date);
		PageInfo<CstService> pageInfo=new PageInfo<>(cstService);
		return new Object[]{pageInfo,userlist};	
}
	//�޸ģ� �ı�״̬
	@RequestMapping("/updateCstService")
	@ResponseBody
	//isΪ�������жϸı�����ĸ�
	public String updateCstService(CstService cstService,Integer is,HttpServletRequest r){
		//����ʱ��
		if(is==1){
			cstService.setSvrDueDate(new Date());
			cstService.setSvrStatus("2");
		}else if(is==2){
			//����
			//��ǰ�û�
			SysUser user =(SysUser)r.getSession().getAttribute("user");
			cstService.setSvrDealId(user.getUserId());
			cstService.setSvrDealBy(user.getUserName());
			cstService.setSvrDealDate(new Date());
			//������
			cstService.setSvrStatus("3");
		}else if(is==3){
			if(cstService.getSvrSatisfy()>=3){
				//�鵵
				cstService.setSvrStatus("4");
			}else if(cstService.getSvrSatisfy()<3){
				//�����⣬���´���
				cstService.setSvrStatus("2");
			}
		}
		int i = iCstService.updaeCstService(cstService);
		return i>0?"true":"false";
	}
	
	//ɾ��
	@RequestMapping("/delectCstService")
	@ResponseBody
	public String delectCstService(Integer csId){
		int i = iCstService.deleteCstService(csId);
		return i>0?"true":"false";
	}
	
	//������� ����ѯһ��
	//is�ж���������ѯ
	@RequestMapping("/SelSvrDealById")
	public ModelAndView SelSvrDealById(Integer csId,Integer is){
		ModelAndView mv=new ModelAndView();
		CstService cstService = iCstService.findcServiceById(csId);
		mv.addObject("cstService", cstService);
		if(is==1){
			//����
			mv.setViewName("CustomerService/ServiceDisposeDialog");
		}else if(is==2){
			//����
			mv.setViewName("CustomerService/ServiceResultDialog");
		}else if(is==3){
			//�鵵
			mv.setViewName("CustomerService/ServiceDetailDialog");		
		}
		return mv;
	}
	
	
	//�ͻ��������
	@RequestMapping("/findService")
	public ModelAndView findService(String dateTime){
		if(dateTime==null || dateTime==""){
			dateTime=null;
		}
		ModelAndView mv=new ModelAndView();
		List<CstService> service = iCstService.findService(dateTime);
		System.out.println(service.size());
		mv.addObject("service", service);
		mv.setViewName("Report/ServerReport");
		return mv;
	}
	
}