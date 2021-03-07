package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gqp
 * @version 1.0
 * @className OrderMapper
 * @descriptin TODO
 * @date 2021/3/7 20:58
 **/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
