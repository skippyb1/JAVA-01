package com.example.demo1.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author gqp
 * @version 1.0
 * @className ShardingsphereConfig
 * @descriptin TODO
 * @date 2021/3/7 23:46
 **/
@Configuration
public class ShardingsphereConfig {

  @Bean
  public DataSource dataSource() {
    // 配置真实数据源
    Map<String, DataSource> dataSourceMap = new HashMap<>();

    // 配置第 1 个数据源
    HikariDataSource dataSource1 = new HikariDataSource();
    dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
    dataSource1.setUsername("root");
    dataSource1.setPassword("");
    dataSourceMap.put("master", dataSource1);

    // 配置第 2 个数据源
    HikariDataSource dataSource2 = new HikariDataSource();
    dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/demo1?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
    dataSource2.setUsername("root");
    dataSource2.setPassword("");
    dataSourceMap.put("slave0", dataSource2);

    List<String> slaveList = new ArrayList<String>();

    slaveList.add("slave0");
    // 配置第 3 个数据源
    HikariDataSource dataSource3 = new HikariDataSource();
    dataSource3.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource3.setJdbcUrl("jdbc:mysql://localhost:3306/demo2?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
    dataSource3.setUsername("root");
    dataSource3.setPassword("");
    dataSourceMap.put("slave1", dataSource3);

    slaveList.add("slave1");
    //配置读写分离规则
    List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
    configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "master", slaveList, "load_balancer"));
    Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
    //ROUND_ROBIN:轮询算法 RANDOM:随机访问算法
    loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
    ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancers);
    List<RuleConfiguration> ruleConfigurationList = new ArrayList<RuleConfiguration>();
    ruleConfigurationList.add(ruleConfiguration);

    DataSource dataSource = null;
    try {
      dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, ruleConfigurationList, new Properties());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dataSource;
  }
}
