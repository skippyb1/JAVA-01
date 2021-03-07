package com.example.demo1;

import com.example.demo1.entity.Order;
import com.example.demo1.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author Skippyb
 */
@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Demo1Application.class, args);
		OrderService bean = run.getBean(OrderService.class);
		bean.insert();
	}

}
