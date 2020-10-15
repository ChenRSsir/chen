package com.turing.service;

import java.util.List;

import com.turing.entity.CstCustomer;

/**
 * �ͻ��ӿ�
 * @author Administrator
 *
 */
public interface CstCustomerService {

	/**
	 * ����һ���ͻ�
	 * @param customer
	 * @return
	 */
	public int addCustomer(CstCustomer customer);
	
	/**
	 * �޸�һ���ͻ�
	 * @param customer
	 * @return
	 */
	public int updateCustomer(CstCustomer customer);
	
	/**
	 * ɾ��һ���ͻ�,�޸Ŀͻ�״̬��û������ɾ��
	 * @param customer
	 * @return
	 */
	public int deleteCustomer(CstCustomer customer);
	
	/**
	 * ͨ�����ƻ�ȡ�ͻ�
	 * @param cusName
	 * @return
	 */
	public CstCustomer findByName(String cusName);
	
	/**
	 * ͨ��������ѯ����
	 * @param customer
	 * @return
	 */
	public List<CstCustomer> findCustomer(String custNo,String custName,String custRegion,String custManagerName,Integer custLevel);

	/**
	 * ��ѯһ������
	 * @param custNo
	 * @return
	 */
    public CstCustomer findCusById(String custNo);
    
    /**
     * ��ѯ����״̬Ϊ�����Ŀͻ��������ж�
     * @return
     */
    public List<CstCustomer> findCus();
    
    /**
     * �ͻ������ѯ
     * @param i
     * @return
     */
    public List<CstCustomer> findCompose(Integer i);
}
