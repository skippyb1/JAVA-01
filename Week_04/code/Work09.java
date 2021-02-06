package com.guanqp.java01.week04;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Work09
 * @Descriptin CountDownLatch 方法
 * @Author Skippyb
 * @Date 2021/2/6 23:12
 * @Version 1.0
 **/
public class Work09 {

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    Obj9 obj9 = new Obj9(countDownLatch);
    new Thread(obj9).start();
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(obj9.getI());
  }


}

class Obj9 extends Thread{

  private int i;

  private CountDownLatch countDownLatch;
  public Obj9(CountDownLatch countDownLatch){
    this.countDownLatch=countDownLatch;
  }
  public int getI() {
    return i;
  }


  public int getNewI(int i){
    for(int v = 0;v<10;v++){
      i++;
      System.out.println("Obj2+"+i);
    }
    return i;
  }


  @Override
  public void run() {
    this.i = getNewI(20);
    countDownLatch.countDown();
  }


}
