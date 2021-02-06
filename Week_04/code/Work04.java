package com.guanqp.java01.week04;

/**
 * @ClassName Work02
 * @Descriptin Thread sleep
 * @Author Skippyb
 * @Date 2021/2/6 20:03
 * @Version 1.0
 **/
public class Work04 {

  public static void main(String[] args) {
    Obj4 obj4 = new Obj4();
    obj4.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("main:"+obj4.getI());
  }


}

class Obj4 extends Thread{

  private int i = 0;

  public int getI() {
    return i;
  }


  @Override
  public void  run (){
    this.i = getNewI(20);

  }


  public int getNewI(int i){
    for(int v = 0;v<10;v++){
      i++;
      System.out.println("Obj2+"+i);
    }
    return i;
  }


}
