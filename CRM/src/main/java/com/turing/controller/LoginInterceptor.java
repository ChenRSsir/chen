package com.turing.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
/**
 * ��½������
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
    /**
     * �����صķ���
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		//��ȡsession
		Object loginUser = request.getSession().getAttribute("user");
		if(loginUser==null){
			//���ص�½ҳ
			response.sendRedirect("/login.html");
			return false;//����
		}else{
			return true;//����
		}
	}
}
