package com.guanqp.java01.week07.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gqp
 * @version 1.0
 * @className JDBCInsert
 * @descriptin TODO
 * @date 2021/3/6 18:57
 **/
public class JDBCInsert {

  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    Runnable r1 = new JDBCInsertThread(1,200000);
    Runnable r2 = new JDBCInsertThread(200001,400000);
    Runnable r3 = new JDBCInsertThread(400001,600000);
    Runnable r4 = new JDBCInsertThread(600001,800000);
    Runnable r5 = new JDBCInsertThread(800001,1000000);
    executorService.execute(r1);
    executorService.execute(r2);
    executorService.execute(r3);
    executorService.execute(r4);
    executorService.execute(r5);
    executorService.shutdown();
//    Runnable r5 = new JDBCInsertThread(1,1000000);
//    r5.run();
  }

  public static void jdbcInsert() throws Exception{



  }

}

class JDBCInsertThread implements Runnable{

  private int begin;
  private int end;

  public JDBCInsertThread(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  @Override
  public void run(){
    String connectionURL = "jdbc:mysql://localhost:3306/demo?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF8&useSSL=false";
    String username = "root";
    String password = "123456";
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(connectionURL, username, "");
      con.setAutoCommit(false);
      String sql = "insert into s_order_info values(?,?,?,?,?,now(),now())";

      long startTime = System.currentTimeMillis();
      PreparedStatement stmt = con.prepareStatement(sql);
      for(int i=begin;i<=end;i++){

        stmt.setLong(1, i);
        stmt.setLong(2, i);
        stmt.setLong(3, i);
        stmt.setDouble(4, 100);
        stmt.setDouble(5, 100);
        stmt.addBatch();// 返回值代表收到影响的行数
        if(i%1000==0){
          stmt.executeBatch();
          stmt.clearBatch();
        }
      }
      con.commit();
      long endTime = System.currentTimeMillis();
      long l = endTime - startTime;
      System.out.println("JDBC Insert ："+l/1000D+"秒");
      stmt.close();
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

//JDBC Insert ：103.362秒(预编译，手动事务)
//JDBC Insert ：17.633秒(预编译，手动事务,批处理)
//JDBC Insert ：11.919秒(预编译，手动事务,批处理 , 多线程(5线程))
