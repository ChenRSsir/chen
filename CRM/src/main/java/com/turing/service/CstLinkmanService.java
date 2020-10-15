package com.turing.service;

import java.util.List;

import com.turing.entity.CstLinkman;

/**
 * ��ϵ�˽ӿ�
 * @author Administrator
 *
 */
public interface CstLinkmanService {

	/**
	 * ���ݿͻ�ID��ѯ��ϵ��
	 * @return
	 */
	public List<CstLinkman> findLinkManById(String custNo);
	/**
	 * ����һ����ϵ��
	 * @param linkman
	 * @return
	 */
	public int addLinkMan(CstLinkman linkman);
	/**
	 * �޸���ϵ��
	 * @param linkman
	 * @return
	 */
	public int updateLinkMan(CstLinkman linkman);
	/**
	 * ����idɾ����ϵ��
	 * @param lid
	 * @return
	 */
	public int deleteLinkMan(Integer lid);
	
	/**
	 * ͨ��id��ѯһ����Ϣ
	 * @param lid
	 * @return
	 */
	public CstLinkman findlmById(Integer lid);
}
