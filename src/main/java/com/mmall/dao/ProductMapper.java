package com.mmall.dao;

import com.mmall.pojo.Product;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_product
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKey(Product record);
}