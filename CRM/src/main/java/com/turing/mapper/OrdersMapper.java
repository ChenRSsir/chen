package com.turing.mapper;

import com.turing.entity.Orders;
import com.turing.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer odrId);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExampleWithRowbounds(OrdersExample example, RowBounds rowBounds);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer odrId);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    //ͨ���ͻ����Ʋ�ѯ�������
    String findOrderTime(@Param("odrCustomer")String odrCustomer);
    //��ѯ�ͻ��ϴ��µ�ʱ��
    String findMaxOrderTime(@Param("odrCustomer")String odrCustomer);
    //��ѯ�����ܼ�
    List<Orders> findOrderTotal(@Param("customer")String customer,@Param("dateTime")String dateTime);
}