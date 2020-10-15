package com.turing.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/***
 * ���Ŀ���������DispathcherServlet
 * @author Administrator
 *
 */
public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	//ȫ��������
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{RootConfig.class};
	}

	//����Spring mvc xml
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{WebConfig.class};
	}

	//����servletMapping
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	
	//���ȫվ��������
    @Override
    protected Filter[] getServletFilters() {
    	// TODO Auto-generated method stub
        //�������������
    	CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
    	//���ñ���
    	encodingFilter.setEncoding("UTF-8");
    	encodingFilter.setForceEncoding(true);
    	return new Filter [] {encodingFilter};
    }
}
