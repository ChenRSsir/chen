package com.turing.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.pagehelper.PageInterceptor;

/**
 * �������ļ�
 * @author Administrator
 *
 */
@Configuration//�յ�����
@ComponentScan(basePackages="com.turing",excludeFilters={@ComponentScan.Filter
(type=FilterType.ANNOTATION,value={EnableWebMvc.class,Controller.class})})
@MapperScan("com.turing.mapper")//ɨ������mapper
@ImportResource("classpath:spring-transaction.xml")//������
public class RootConfig {

	//����DataSource
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource=new BasicDataSource();
		//��Ҫ��
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/crm");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		//��������
        dataSource.setInitialSize(5);//��ʼ����������
        dataSource.setMaxIdle(10);//������������
        dataSource.setMinIdle(2);//��С����������
        dataSource.setMaxTotal(100);//���������
        dataSource.setMaxWaitMillis(3000);//���ȴ�ʱ��  3����
        return dataSource;
	}
	
	//����
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
		//��������Դ
		sessionFactoryBean.setDataSource(dataSource);
		//���÷�ҳ������
		PageInterceptor pageInterceptor=new PageInterceptor();
		//���������Ҫ�Ĳ�������
		Properties properties=new Properties();
		//�������ݿⷽ��
		properties.setProperty("helperDialect", "mysql");
		//���÷�ҳ��������
		properties.setProperty("reasonable", "true");
		
		pageInterceptor.setProperties(properties);
         //�����������õ�SqlSessionFactory
		sessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
		return sessionFactoryBean.getObject();
		
	}
	
	//������
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
}
