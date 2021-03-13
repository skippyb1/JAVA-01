package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gqp
 * @version 1.0
 * @className Order
 * @descriptin TODO
 * @date 2021/3/7 18:45
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("s_order_info")
public class Order implements Serializable {

  @TableId(type = IdType.ASSIGN_ID)
  private Long id;

  private Long productId;

  private Long userId;

  private Double totalAmount;

  private Double payPrice;

  private Date createTime=new Date();

  private Date updateTime=new Date();
}
