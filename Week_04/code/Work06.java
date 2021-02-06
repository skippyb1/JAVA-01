package com.guanqp.java01.week04;

import java.util.concurrent.*;

/**
 * @ClassName Work06
 * @Descriptin ThreadPool  Future 方法
 * @Author Skippyb
 * @Date 2021/2/6 22:47
 * @Version 1.0
 **/
public class Work06 {

  public static void main(String[] args) {

    ExecutorService executor = Executors.newCachedThreadPool();
    Future<Integer> result = executor.submit(new Callable<Integer>() {
      public Integer call() throws Exception {
        return 100;
      }
    });
    executor.shutdown();
    try {
      System.out.println("result:" + result.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }


}
