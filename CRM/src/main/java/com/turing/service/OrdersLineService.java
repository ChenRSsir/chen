package com.turing.service;

import java.util.List;

import com.turing.entity.OrdersLine;

/**
 * ��������ӿ�
 * @author Administrator
 *
 */
public interface OrdersLineService {

	/**
	 * ͨ������id��ѯ����
	 * @param oddOrderId
	 * @return
	 */
	public List<OrdersLine> findOrderLineByOid(Integer oddOrderId);
	
	/**
	 * ͨ������id��ѯ�����ܼ�
	 * @param oddOrderId
	 * @return
	 */
	public Integer findOrderTotal(Integer oddOrderId);
}
