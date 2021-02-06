package com.guanqp.java01.week04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Work10
 * @Descriptin CyclicBarrier 方法
 * @Author Skippyb
 * @Date 2021/2/7 0:17
 * @Version 1.0
 **/
public class Work10 {

  public static void main(String[] args) {

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
      System.out.println("回调");
    } );

    Obj10 obj10 = new Obj10(cyclicBarrier);
    new Thread(obj10).start();

    try {
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println(obj10.getI());

  }


}

class Obj10 extends Thread{

  private int i;
  private CyclicBarrier cyclicBarrier;

  public Obj10(CyclicBarrier cyclicBarrier){
    this.cyclicBarrier=cyclicBarrier;
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
    try {
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }


}
