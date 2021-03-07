package com.guanqp.java01.week07.hibernate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Entity
@Table(name="s_order_info")
public class Order implements Serializable {

  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  private Long id;

  @Column(name="product_id")
  private Long productId;

  @Column(name="user_id")
  private Long userId;

  @Column(name="total_amount",precision = 16,scale = 2)
  private BigDecimal totalAmount;

  @Column(name="pay_price",precision = 16,scale = 2)
  private BigDecimal payPrice;

  @Column(name="create_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createTime=new Date();

  @Column(name="update_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateTime=new Date();
}
