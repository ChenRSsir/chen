package com.turing.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.turing.controller.LoginInterceptor;

/**
 * �����ļ�
 * @author Administrator
 *
 */

@Configuration//�յ������ļ�
@ComponentScan("com.turing.controller")//ɨ�����и�������
@EnableWebMvc//����Spring mvc ����ע��
public class WebConfig extends WebMvcConfigurerAdapter{

	//����ģ�������
	@Bean
	public ITemplateResolver iTemplateResolver(ApplicationContext applicationContext){
		//ģ�������
		SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
		//���������� IOC����
		templateResolver.setApplicationContext(applicationContext);
		//����ǰ׺
		templateResolver.setPrefix("/");
		//���ú�׺
		templateResolver.setSuffix(".html");
		//����ģ������
		templateResolver.setTemplateMode(TemplateMode.HTML);
		//����ʱΪ�˵��Է��㣬����ҳ�滺��
		templateResolver.setCacheable(false);
        //���ñ���
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
	
	//Spring ģ������
	@Bean
	public ISpringTemplateEngine templateEngine(ITemplateResolver iTemplateResolver){
		//����ģ������
		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
		//����֧��Spring��IE���ʽ
		templateEngine.setEnableSpringELCompiler(true);
		//����ģ������Ľ�����
		templateEngine.setTemplateResolver(iTemplateResolver);
		
		return templateEngine;
	}
	
	//��ͼ������
	@Bean
	public ViewResolver viewResolver(ISpringTemplateEngine templateEngine){
		//����ThymeleafView����ͼ������
		ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
		//����ģ��
		viewResolver.setTemplateEngine(templateEngine);
		//���ñ��� 
		viewResolver.setCharacterEncoding("UTF-8");
		
		return viewResolver;
	}
	
	//��д����
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
        //������̬��Դ����
		configurer.enable();
	}
	
	//ע��������
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
		//���ص�·��
		interceptor.addPathPatterns("/**");
		//�����ص�·��������ö��Ÿ���
		interceptor.excludePathPatterns("/userLogin");
	}
}
