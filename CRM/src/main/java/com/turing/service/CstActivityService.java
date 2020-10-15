package com.turing.service;

import java.util.List;

import com.turing.entity.CstActivity;

/**
 * ��ӿ���
 * @author Administrator
 *
 */
public interface CstActivityService {

	/**
	 * ���ݿͻ�id��ȡ�����
	 * @param custNo
	 * @return
	 */
	public List<CstActivity> findActList(String custNo);
	
	/**
	 * ͨ��id��ȡ�
	 * @param actId
	 * @return
	 */
	public CstActivity findActById(Integer actId);
	
	/**
	 * �����
	 * @param activity
	 * @return
	 */
	public int addAct(CstActivity activity);
	
	/**
	 * �޸Ļ
	 * @param activity
	 * @return
	 */
	public int updateAct(CstActivity activity);
	
	/**
	 * ͨ��idɾ���
	 * @param actId
	 * @return
	 */
	public int deleteAct(Integer actId);
	
}
