package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int insertSelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_cart
     *
     * @mbg.generated Mon Jan 14 20:55:44 CST 2019
     */
    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    List<Cart> selectCartByUserId(Integer user);

    int selectCartProductCheckStatusByUserId(Integer userId);

    int deleteByUserIdProducts(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);

    int checkOrUnCheckedProduct(@Param("userId") Integer userId, @Param("checked") Integer checked, @Param("productId") Integer productId);

    int selectCartProdyctCount(@Param("userId") Integer userId);
}