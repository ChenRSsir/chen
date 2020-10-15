package com.turing.service;

import java.util.List;

import com.turing.entity.CstLost;

/**
 * �ͻ���ʧ�ӿ�
 * @author Administrator
 *
 */
public interface CstLostService {

	/**
	 * ������ʧ
	 * @param cstLost
	 * @return
	 */
	public int addCusLost(CstLost cstLost);
	
	/**
	 * �޸�
	 * @param cstLost
	 * @return
	 */
	public int updateCusLost(CstLost cstLost);
	
	/**
	 * ������ѯ
	 * @param lstCustName
	 * @param lstCustManagerName
	 * @param lstStatus
	 * @return
	 */
	public List<CstLost> findCusLost(String lstCustName,String lstCustManagerName,String lstStatus);

    /**
     * ͨ��id��ѯһ������
     * @param lid
     * @return
     */
    public CstLost findClostById(Integer lid);
    
    /**
     * ��ʧ��Ϣ����
     * @param lstCustName
     * @param lstCustManagerName
     * @return
     */
    public List<CstLost> findLost(String lstCustName,String lstCustManagerName);
}
