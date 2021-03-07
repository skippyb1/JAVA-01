package com.guanqp.java01.week07.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

/**
 * @author gqp
 * @version 1.0
 * @className HibernateInsert
 * @descriptin TODO
 * @date 2021/3/7 18:03
 **/
public class HibernateInsert {

  public static void main(String[] args) throws Exception {
    SessionFactory sessionFactory = setUp();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    long startTime = System.currentTimeMillis();
    for(long i=1;i<=1000000;i++){
      session.save(new Order(i,i,i,new BigDecimal(100),new BigDecimal(100),new Date(),new Date()));
      if(i%200==0){
        session.flush();
        session.clear();
      }
    }
    transaction.commit();
    long endTime = System.currentTimeMillis();
    long l = endTime - startTime;
    System.out.println("JDBC Insert ："+l/1000D+"秒");
    session.close();
    sessionFactory.close();
  }

  public static SessionFactory setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure(new File(HibernateInsert.class.getResource("hibernate.cfg.xml").getFile())) // configures settings from hibernate.cfg.xml
        .build();
    try {
      SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
      return sessionFactory;
    }
    catch (Exception e) {
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy( registry );
    }
    return null;
  }

}
//35.551秒
//批处理 160.088秒