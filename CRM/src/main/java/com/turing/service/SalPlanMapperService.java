package com.turing.service;

import java.util.List;

import com.turing.entity.SalPlan;

/**
 * ���ۼƻ�ҵ����
 * @author Administrator
 *
 */
public interface SalPlanMapperService {

	/**
	 * ͨ�����ۻ���id��ѯ�ƻ�����
	 * @return
	 */
	public List<SalPlan> findPlanList(Integer saleChanceId);
	
	/**
	 * ���һ���ƻ�
	 * @param plan
	 * @return
	 */
	public int addPlan(SalPlan plan);
	
	/**
	 * �޸�һ���ƻ�
	 * @param plan
	 * @return
	 */
	public int updatePlan(SalPlan plan);
	
	/**
	 * ɾ��һ���ƻ�
	 * @param planId
	 * @return
	 */
	public int deletePlan(Integer plaId);
}
