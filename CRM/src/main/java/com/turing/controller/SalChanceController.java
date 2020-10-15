package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.CstCustomer;
import com.turing.entity.SalChance;
import com.turing.entity.SysUser;
import com.turing.service.CstCustomerService;
import com.turing.service.SalChanceService;
import com.turing.service.SysUserService;

/**
 * ���ۻ�����Ʋ�
 * @author Administrator
 *
 */
@Controller
public class SalChanceController {
	
	//���ۻ���
	@Autowired
	private SalChanceService salChanceService;
	//�û�����ע��
	@Autowired
	private SysUserService sysUserService;
	//�ͻ�����
	@Autowired
	private CstCustomerService cstCustomerService;

	//��ѯ ���ۻ���
	@RequestMapping("/SalChance")
	public String SalChance(){
	  return "sale/SaleChance";	
	}
	//��ѯ  �ͻ�����
	@RequestMapping("/salManager")
	public String saleManager(){
		return "sale/saleManager";
	}
	
	// ��ѯ
	@RequestMapping("/findSalChanceByOne")
	@ResponseBody
	public Object findSalChanceByOne(Integer pageNum,Integer pageSize,String chcCustName, String chcLinkman, String chcTitle,Integer chcStatus){
		if(chcCustName==null){
			chcCustName="";
		}
		if(chcLinkman==null){
			chcLinkman="";
		}
		if(chcTitle==null){
			chcTitle="";
		}
		PageHelper.startPage(pageNum, pageSize);
		List<SalChance> list=null;
		//�ж������ۻ��ỹ�ǿͻ�����
		if(chcStatus==null){
			//���ۻ���
		    list = salChanceService.findSalChanceByOne(chcCustName, chcLinkman, chcTitle);
		}else{
		 if(chcStatus==0){
			//��ѯ����
			list=salChanceService.findSalChanceByNoOne(chcCustName, chcLinkman, chcTitle, chcStatus);
		 }else{
			//��ѯ���� 2 3 4
			list=salChanceService.findSalChanceByNoOne(chcCustName, chcLinkman, chcTitle, chcStatus);
		      }
		}
		 PageInfo<SalChance> pageInfo=new PageInfo<>(list);
	     return pageInfo;
	}
	//����
	@RequestMapping("/add")
	public String add(){
		return "sale/AddSale";
	}
	
	@RequestMapping("/addSale")
	@ResponseBody
	public String addSale(SalChance salChance,String CreateDate,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//���贴����id
		SysUser user =(SysUser)request.getSession().getAttribute("user");
		if(salChance.getChcCreateBy().equals(user.getUserName())){
			salChance.setChcCreateId(user.getUserId());
			salChance.setChcCreateDate(sdf.parse(CreateDate));
			//����Ĭ��Ϊ1
			salChance.setChcStatus(1);
		}
		int i = salChanceService.addSalChance(salChance);
		
		return i>0?"true":"false";
	}
	
	/**
	 * ��ѯһ������
	 * @param id ����id
	 * @param i �������������
	 * @return
	 */
	@RequestMapping("/findSaleById")
	public ModelAndView findSaleById(Integer id,Integer i,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		SalChance salChance = salChanceService.findSalChanceById(id);
		//��ȡ�û�
		List<SysUser> users = sysUserService.getUsers();
		mv.addObject("salChance", salChance);
		if(i==1){
			mv.setViewName("sale/AllotSale");
			mv.addObject("users", users);
		}else if(i==2){
		    mv.setViewName("sale/EditSale");
		}
		return mv;
	}
	
	//�޸�
	@RequestMapping("/updateSale")
	@ResponseBody
	public String updateSale(SalChance chance,String DueDate) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		int i=0;
        //�ж��Ƿ���ָ��ʱ�䣬����ָ�ɱ�û�����޸ı�
		if(DueDate!=null){
			//��ȡ��ָ����id
			SysUser user = sysUserService.login(chance.getChcDueTo());
			chance.setChcDueId(user.getUserId());
			chance.setChcDueDate(sdf.parse(DueDate));
			//�޸�״̬Ϊ��ָ��
			chance.setChcStatus(2);
			i= salChanceService.updateSalChance(chance);
		}else{
			i= salChanceService.updateSalChance(chance);
		}
		 
		return i>0?"true":"false";
	}
	
	//ɾ��
	@RequestMapping("/deleteSale")
	@ResponseBody
	public Integer deleteSale(Integer id,HttpServletRequest request){
		//��ȡ��ǰ�û���Ϣ
		SysUser user =(SysUser)request.getSession().getAttribute("user");
		Integer userId = user.getUserId();
		//��ȡ������¼����Ϣ ȡ������
		SalChance salChance = salChanceService.findSalChanceById(id);
		//���ص���Ϣ
		int i=0;
		//�ж��Ƿ��ǵ�ǰ��½�û�
		if(salChance.getChcCreateId()==userId){
			i = salChanceService.deleteSalChance(id);
		}else{
			i=2;
		}	
		return i;
	}
	//�޸�״̬
	@RequestMapping("/updateChcStatus")
	@ResponseBody
	public String updateChcStatus(SalChance chance){
		int i=0;
		//4Ϊʧ�� 3Ϊ�ɹ��½��ͻ�
		if(chance.getChcStatus()==4){
		  i = salChanceService.updateSalChance(chance);
		}else if(chance.getChcStatus()==3){
		  i = salChanceService.updateSalChance(chance);
		  //�½��ͻ�
		  SalChance chance2 = salChanceService.findSalChanceById(chance.getChcId());
		  CstCustomer c=new CstCustomer();
		  //�ͻ����
		  c.setCustNo("KH10010"+chance2.getChcId());
		  //�ͻ�����
		  c.setCustName(chance2.getChcCustName());
		  //�ͻ�����id
		  c.setCustManagerId(chance2.getChcDueId());
		  //�ͻ���������
		  c.setCustManagerName(chance2.getChcDueTo());
		  //�ͻ��绰
		  c.setCustTel(chance2.getChcTel());
		  //״̬ Ĭ������
		  c.setCustStatus("1");
		  //ʱ�䣬Ĭ��ϵͳʱ��
		  c.setCustCreateDate(new Date());
		  //��Ĭ��Ϊ��ͨ�ͻ�
		  c.setCustLevel(13);
		  //��ַ��Ϊ��
		  c.setCustRegion("");
		  //������ӷ���
		  cstCustomerService.addCustomer(c);
		}
		return i>0?"true":"false";
	}
}
