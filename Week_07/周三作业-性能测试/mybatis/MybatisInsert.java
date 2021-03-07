package com.guanqp.java01.week07.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author gqp
 * @version 1.0
 * @className MybatisInsert
 * @descriptin TODO
 * @date 2021/3/7 1:00
 **/
public class MybatisInsert {

  public static void main(String[] args) {
    Properties p=new Properties();
    p.setProperty("driver","com.mysql.jdbc.Driver");
    p.setProperty("url","jdbc:mysql://localhost:3306/demo?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF8&useSSL=false");
    p.setProperty("username","root");
    p.setProperty("password","");
    PooledDataSourceFactory pool = new PooledDataSourceFactory();
    pool.setProperties(p);
    DataSource dataSource = pool.getDataSource();
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    configuration.addMapper(OrderMapper.class);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
    long startTime = System.currentTimeMillis();
    for(long i=1;i<=1000000;i++){
      mapper.insert(i,i,i,100D,100D);
    }
    sqlSession.commit();
    long endTime = System.currentTimeMillis();
    long l = endTime - startTime;
    System.out.println("JDBC Insert ："+l/1000D+"秒");
  }

}
//MybatisInsert ExecutorType.BATCH 批处理 26.29秒
//MybatisInsert ExecutorType.SIMPLE  207.851秒
