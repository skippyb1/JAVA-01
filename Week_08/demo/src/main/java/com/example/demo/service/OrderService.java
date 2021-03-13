package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Order;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.transaction.annotation.Transactional;

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


  Boolean insertBatch();


  Boolean other();
}
