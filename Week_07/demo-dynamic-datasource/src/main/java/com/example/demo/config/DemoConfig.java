package com.example.demo.config;

import com.example.demo.datasource.DynamicDataSource;
import com.example.demo.properties.DatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gqp
 * @version 1.0
 * @className DemoConfig
 * @descriptin TODO
 * @date 2021/3/7 21:01
 **/
@Configuration
public class DemoConfig {

  @Autowired
  private DatasourceProperties properties;

  @Bean
  public DataSource dataSource(){

    DatasourceProperties.DatasourceProperty master = properties.getMaster();
    List<DatasourceProperties.DatasourceProperty> slaves = properties.getSlaves();
    Map<Object,Object> p = new HashMap<>();
    DataSource masterDatasource = createDataSource(master);
    p.put("master",masterDatasource);
    List<String> dataSources = new ArrayList<>();
    for (int i = 0; i < slaves.size() ; i++ ) {
      DatasourceProperties.DatasourceProperty slave = slaves.get(i);
      DataSource slaveDataSource = createDataSource(slave);
      p.put("slave" + i,slaveDataSource);
      dataSources.add("slave" + i);
    }
    DynamicDataSource dynamicDataSource = new DynamicDataSource("master",dataSources);
    dynamicDataSource.setTargetDataSources(p);
    dynamicDataSource.setDefaultTargetDataSource(masterDatasource);
    return dynamicDataSource;
  }
  private DataSource createDataSource(DatasourceProperties.DatasourceProperty property){

    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(property.getDriver());
    dataSourceBuilder.url(property.getUrl());
    dataSourceBuilder.username(property.getUsername());
    dataSourceBuilder.password(property.getPassword());
    return dataSourceBuilder.build();

  }
}
