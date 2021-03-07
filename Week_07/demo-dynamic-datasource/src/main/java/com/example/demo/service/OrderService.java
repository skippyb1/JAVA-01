package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.annotations.ReadOnly;
import com.example.demo.entity.Order;

import java.util.List;

/**
 * @author gqp
 * @version 1.0
 * @className OrderService
 * @descriptin TODO
 * @date 2021/3/7 22:32
 **/
public interface OrderService extends IService<Order> {
  @ReadOnly
  void insert();

  @ReadOnly(only = true)
  List<Order> select();
}
