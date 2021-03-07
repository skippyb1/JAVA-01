package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		OrderService bean = run.getBean(OrderService.class);
		List<Order> select1 = bean.select();
		List<Order> select2 = bean.select();
		List<Order> select3 = bean.select();
		List<Order> select4 = bean.select();
		List<Order> select5 = bean.select();
		System.out.println(select1);
		System.out.println(select2);
		System.out.println(select3);
		System.out.println(select4);
		System.out.println(select5);

	}

}
