package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.annotations.ReadOnly;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author gqp
 * @version 1.0
 * @className OrderServiceImpl
 * @descriptin TODO
 * @date 2021/3/7 22:33
 **/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


  @Override
  @ReadOnly
  public void insert(){
    this.save(new Order(1L,1L,1L,100D,100D,new Date(),new Date()));
  }

  @Override
  @ReadOnly(only = true)
  public List<Order> select(){
    return this.list();
  }
}
