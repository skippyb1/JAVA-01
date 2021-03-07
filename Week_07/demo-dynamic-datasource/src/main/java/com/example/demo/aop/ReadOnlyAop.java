package com.example.demo.aop;

import com.example.demo.annotations.ReadOnly;
import com.example.demo.datasource.DatasourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author gqp
 * @version 1.0
 * @className ReadOnlyAop
 * @descriptin TODO
 * @date 2021/3/7 22:23
 **/
@Aspect
@Component
public class ReadOnlyAop {

  @Before("@annotation(com.example.demo.annotations.ReadOnly)")
  public void changeDataSource(JoinPoint joinPoint){
    MethodSignature signature =(MethodSignature) joinPoint.getSignature();
    ReadOnly annotation = signature.getMethod().getAnnotation(ReadOnly.class);
    if(annotation.only()){
      DatasourceContextHolder.setDatasourceType("slave");
    }else{
      DatasourceContextHolder.setDatasourceType("master");
    }

  }

}
