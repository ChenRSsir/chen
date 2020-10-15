package com.turing.service;

import java.util.Date;
import java.util.List;

import com.turing.entity.CstService;
/**
 * �ͻ�����ӿ�
 * @author Administrator
 *
 */
public interface ICstService {

	/**
	 * ��������
	 * @param cstService
	 * @return
	 */
	public int addCstService(CstService cstService);
	
	/**
	 * �޸ķ���
	 * @param cstService
	 * @return
	 */
	public int updaeCstService(CstService cstService);
	
	/**
	 * ɾ������
	 * @param csId
	 * @return
	 */
	public int deleteCstService(Integer csId);
	
	/**
	 * ��ѯһ����¼
	 * @param csId
	 * @return
	 */
	public CstService findcServiceById(Integer csId);
	

	/**
	 * ���Ӳ�ѯ
	 * @param cstService
	 * @return
	 */
	public List<CstService> findCstService(CstService cs,Date bDate);
	
	/**
	 * �ͻ��������
	 * @param dateTime
	 * @return
	 */
	public List<CstService> findService(String dateTime);
}
