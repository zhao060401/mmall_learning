package com.mmall.dao;

import com.mmall.pojo.Shipping;

public interface ShippingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_shipping
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_shipping
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insert(Shipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_shipping
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insertSelective(Shipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_shipping
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    Shipping selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_shipping
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKeySelective(Shipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_shipping
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKey(Shipping record);
}