package com.turing.service;

import java.util.List;

import com.turing.entity.SysUser;

/**
 * �û�ҵ����
 * @author Administrator
 *
 */
public interface SysUserService {

	/**
	 * ��½
	 * @return ��ѯ�����û�
	 */
	public SysUser login(String name);
	/**
	 * ��ѯ�����Լ�������û�
	 * @param name ��ǰ�û�
	 * @return
	 */
	public List<SysUser> getUsers();
}
