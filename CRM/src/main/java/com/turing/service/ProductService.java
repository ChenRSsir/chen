package com.turing.service;

import java.util.List;

import com.turing.entity.Product;

/**
 * ��Ʒҵ����
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * ͨ��������ѯ��Ʒ
	 * @param prodName ����
	 * @param prodType ����
	 * @param prodBatch ����
	 * @return
	 */
	public List<Product> findPro(String prodName,String prodType,String prodBatch);
}
