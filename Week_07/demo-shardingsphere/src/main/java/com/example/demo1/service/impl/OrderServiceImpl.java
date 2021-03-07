package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1.entity.Order;
import com.example.demo1.mapper.OrderMapper;
import com.example.demo1.service.OrderService;
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
  public void insert(){
    this.save(new Order(1L,1L,1L,100D,100D,new Date(),new Date()));
  }

  @Override
  public List<Order> select(){
    return this.list();
  }
}
