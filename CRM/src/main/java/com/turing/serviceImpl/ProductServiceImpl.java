package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.Product;
import com.turing.entity.ProductExample;
import com.turing.mapper.ProductMapper;
import com.turing.service.ProductService;
/**
 * ��Ʒҵ��ʵ����
 * @author Administrator
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> findPro(String prodName, String prodType, String prodBatch) {
        ProductExample example=new ProductExample();
        example.createCriteria().andProdNameLike("%"+prodName+"%").andProdTypeLike("%"+prodType+"%").andProdBatchLike("%"+prodBatch+"%");
		return productMapper.selectByExample(example);
	}

}
