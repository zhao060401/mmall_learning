package com.mmall.dao;

import com.mmall.pojo.OrderItem;

public interface OrderItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order_item
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order_item
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insert(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order_item
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insertSelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order_item
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    OrderItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order_item
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order_item
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKey(OrderItem record);
}