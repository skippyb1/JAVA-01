package com.guanqp.java01.week07.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

  @Insert("insert into s_order_info values(#{id},#{productId},#{userId},#{totalAmount},#{payPrice},now(),now())")
  int insert(@Param("id") Long id,
             @Param("productId") Long productId,
             @Param("userId") Long userId,
             @Param("totalAmount") Double totalAmount,
             @Param("payPrice") Double payPrice);
}
