package com.guanqp.java01.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Work01
 * @Descriptin FutureTask Callable方法
 * @Author Skippyb
 * @Date 2021/2/6 20:03
 * @Version 1.0
 **/
public class Work05 {

  public static void main(String[] args) {

    FutureTask futureTask = new FutureTask(new

                                               Callable() {

       @Override
      public Object call() throws Exception {
        return "asd";
      }


                                               });
    new Thread(futureTask).start();
    Object o = null;
    try {
      o = futureTask.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println(o);

  }


}
