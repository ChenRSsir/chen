package com.turing.service;

import java.util.List;

import com.turing.entity.SalChance;

/**
 * ���ۻ���ҵ����
 * @author Administrator
 *
 */
public interface SalChanceService{

	/**
	 * ��ѯδָ�ɵ����ۻ���
	 * @return
	 */
	public List<SalChance> findSalChanceByOne(String chcCustName,String chcLinkman,String chcTitle);
	
	/**
	 * ��ѯ����δָ�ɵ����ۻ���
	 * @return
	 */
	public List<SalChance> findSalChanceByNoOne(String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus);
	
	/**
	 * ��ѯһ����Ϣ
	 * @param id
	 * @return
	 */
	public SalChance findSalChanceById(Integer id);
	
	/**
	 * ����һ������
	 * @param chance
	 * @return
	 */
	public int addSalChance(SalChance chance);
	
	/**
	 * �޸�һ������
	 * @param chance
	 * @return
	 */
	public int updateSalChance(SalChance chance);
	
	/**
	 * ����idɾ��һ������
	 * @param id
	 * @return
	 */
	public int deleteSalChance(Integer id);
}
