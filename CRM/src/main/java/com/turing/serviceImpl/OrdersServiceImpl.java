package com.turing.serviceImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.Orders;
import com.turing.entity.OrdersExample;
import com.turing.mapper.OrdersMapper;
import com.turing.service.OrdersService;
/**
 * ����ҵ����
 * @author Administrator
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Override
	public List<Orders> findOrderByCus(String cusName) {
		OrdersExample example=new OrdersExample();
		example.createCriteria().andOdrCustomerEqualTo(cusName);
		List<Orders> list = ordersMapper.selectByExample(example);
		//ʹ��Collections.sort����
		Collections.sort(list,new Comparator<Orders>() {
			//��дcompare����
			@Override
			public int compare(Orders o1, Orders o2) {
				//��ʱ������ o1С o2��
				return o2.getOdrDate().compareTo(o1.getOdrDate());
			}
		});		
		return list;	
	}

	@Override
	public Orders findOrderById(Integer oid) {
		return ordersMapper.selectByPrimaryKey(oid);
	}

	@Override
	public String findOrderTime(String odrCustomer) {
		return ordersMapper.findOrderTime(odrCustomer);
	}

	@Override
	public String findMaxOrderTime(String odrCustomer) {
		return ordersMapper.findMaxOrderTime(odrCustomer);
	}

	@Override
	public List<Orders> findOrderTotal(String customer, String dateTime) {
		return ordersMapper.findOrderTotal("%"+customer+"%", dateTime);
	}

}
