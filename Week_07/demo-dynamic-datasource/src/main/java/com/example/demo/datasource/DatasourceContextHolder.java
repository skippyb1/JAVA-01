package com.example.demo.datasource;

/**
 * @author gqp
 * @version 1.0
 * @className DatasourceContextHolder
 * @descriptin TODO
 * @date 2021/3/7 22:11
 **/
public class DatasourceContextHolder {
  private static final ThreadLocal<String> contextHolder=new ThreadLocal<>();
  public static void setDatasourceType(String type) {
    contextHolder.set(type);
  }
  public static String getDatasourceType() {
    return contextHolder.get();
  }
}
