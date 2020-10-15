package com.turing.service;

import java.util.List;

import com.turing.entity.Orders;

/**
 * �����ӿ���
 * @author Administrator
 *
 */
public interface OrdersService {

	/**
	 * ͨ���ͻ���ѯ����
	 * @param cusName
	 * @return
	 */
	public List<Orders> findOrderByCus(String cusName);
	
	/**
	 * ͨ��id��ѯ����
	 * @param oid
	 * @return
	 */
	public Orders findOrderById(Integer oid);
	/**
	 * ͨ���ͻ����Ʋ�ѯ�����������
	 * @param odrCustomer
	 * @return
	 */
	public String findOrderTime(String odrCustomer);
	
	/**
	 * ��ѯ�ͻ��ϴ��µ�ʱ��
	 * @param odrCustomer
	 * @return
	 */
	public String findMaxOrderTime(String odrCustomer);
	
	/**
	 * ��ѯ�����ܼ�
	 * @param customer
	 * @param dateTime
	 * @return
	 */
	public List<Orders> findOrderTotal(String customer,String dateTime);
}
