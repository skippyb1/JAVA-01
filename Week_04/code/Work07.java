package com.guanqp.java01.week04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName Work07
 * @Descriptin CompletableFuture supplyAsync
 * @Author Skippyb
 * @Date 2021/2/6 22:58
 * @Version 1.0
 **/
public class Work07 {

  public static void main(String[] args) {
    String s = null;
    try {
      s = CompletableFuture.supplyAsync(() -> "asd").get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println(s);
  }


}
