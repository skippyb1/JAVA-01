package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		/*UserService bean = run.getBean(UserService.class);
		bean.save(new User(2L,"1","1","1",
				1L,"1",1L,1L,1L,new Date(),new Date()));
		bean.save(new User(3L,"1","1","1",
				1L,"1",1L,1L,1L,new Date(),new Date()));
		bean.save(new User(4L,"1","1","1",
				1L,"1",1L,1L,1L,new Date(),new Date()));
		bean.save(new User(5L,"1","1","1",
				1L,"1",1L,1L,1L,new Date(),new Date()));*/
		OrderService bean = run.getBean(OrderService.class);
		bean.insertBatch();
	}

}
