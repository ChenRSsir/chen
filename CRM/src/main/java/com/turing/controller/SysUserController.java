package com.turing.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.SysUser;
import com.turing.service.SysUserService;

/**
 * �û�������
 * @author Administrator
 *
 */
@Controller
public class SysUserController {

	@Autowired
	private SysUserService userService;
	//��½
	@RequestMapping("/userLogin")
	public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, String textfield,String textfield2) throws Exception{
		ModelAndView mv=new ModelAndView();
		PrintWriter out = response.getWriter();
		if(textfield==null){
			 out.print("<script>alert('���ȵ�½��');</script>");
			mv.setViewName("/login");
		}else{
		SysUser user2 = userService.login(textfield);
		if(user2==null){
			//�˺Ų��ԣ���½ʧ��
		    out.print("<script>alert('��½ʧ�ܣ��˺��������');</script>");
			mv.setViewName("login");
		}else{
			//������ȷ
			if(user2.getUserPassword().equals(textfield2)){
				if(user2.getUserFlag()==1){
				   //�ɹ�
					request.getSession().setAttribute("user", user2);
			       mv.setViewName("Main");
				}else{
				   //Ȩ�޲���
					out.print("<script>alert('��½ʧ�ܣ�Ȩ�޲��㡣');</script>");
					mv.setViewName("login");
				}
			}else{
				//�������
				out.print("<script>alert('��½ʧ�ܣ������������');</script>");
				mv.setViewName("login");
			}
		}
	}	
		return mv;
}
	/**
	 * �˳�
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		if(request.getSession().getAttribute("user")!=null){
			request.getSession().removeAttribute("user");
			mv.setViewName("login");
		}else{
			mv.setViewName("login");
		}
		return mv;
	}
}
