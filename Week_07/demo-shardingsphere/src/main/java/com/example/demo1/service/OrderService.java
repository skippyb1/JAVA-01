package com.example.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1.entity.Order;

import java.util.List;

/**
 * @author gqp
 * @version 1.0
 * @className OrderService
 * @descriptin TODO
 * @date 2021/3/7 22:32
 **/
public interface OrderService extends IService<Order> {

  void insert();

  List<Order> select();
}
