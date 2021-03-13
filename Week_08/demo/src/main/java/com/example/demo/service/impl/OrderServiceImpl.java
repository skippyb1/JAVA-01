package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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

  @Override
  @Transactional
  @ShardingTransactionType(TransactionType.XA)
  public Boolean insertBatch(){
    List<Order> orders = Arrays.asList(
        new Order(null, 1L, 1L, 100D, 100D, new Date(), new Date()),
        new Order(null, 1L, 2L, 100D, 100D, new Date(), new Date()),
        new Order(null, 1L, 3L, 100D, 100D, new Date(), new Date()),
        new Order(null, 1L, 4L, 100D, 100D, new Date(), new Date()),
        new Order(null, 1L, 5L, 100D, 100D, new Date(), new Date()),
        new Order(null, 1L, 6L, 100D, 100D, new Date(), new Date()),
        new Order(null, 1L, 7L, 100D, 100D, new Date(), new Date())
    );
    boolean b = this.saveBatch(orders);
    if(orders.size()>1){
      throw new RuntimeException("error");
    }
    return b;

  }
  @Override
  @Transactional
  @ShardingTransactionType(TransactionType.XA)
  public Boolean other(){
    List<Order> list = this.list();
    for(Order o:list){
      if(o.getId()%3==0){
        System.out.println("delete:"+o.getId());
        this.removeById(o.getId());
      }
      if(o.getId()%7==0){
        System.out.println("update:"+o.getId());
        o.setPayPrice(20D);
        this.update(o,new LambdaQueryWrapper<Order>().eq(Order::getId,o.getId()).eq(Order::getUserId,o.getUserId()));
      }
    }
    return true;
  }
}
