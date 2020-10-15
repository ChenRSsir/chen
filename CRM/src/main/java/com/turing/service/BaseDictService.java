package com.turing.service;

import java.util.List;

import com.turing.entity.BaseDict;

/**
 * �����ֵ�ҵ����
 * @author Administrator
 *
 */
public interface BaseDictService {

	/**
	 * ��ѯ���������ֵ�
	 * @return ���ݼ���
	 */
	public List<BaseDict> findBaseDict();
	
	/**
	 * ����������ѯ�����ֵ�
	 * @param baseDict
	 * @return
	 */
	public List<BaseDict> findBaseDictByWhere(BaseDict baseDict);
	
	/**
	 * ��ѯһ������
	 * @param id
	 * @return һ������
	 */
	public BaseDict findBaseDictById(Integer id);
	
	/**
	 * ���һ������
	 * @param baseDict
	 * @return ��Ӱ�������
	 */
	public int insert(BaseDict baseDict);
	
	/**
	 * �޸�һ������
	 * @param baseDict
	 * @return ��Ӱ�������
	 */
	public int update(BaseDict baseDict);
	
	/**
	 * ɾ��һ������
	 * @param id
	 * @return ��Ӱ�������
	 */
	public int delete(Integer id);
	
	/**
	 * ͨ��ֵ��ȡ��Ŀ
	 * @param dictValue
	 * @return
	 */
	public List<BaseDict> findItem(String dictValue);
}
