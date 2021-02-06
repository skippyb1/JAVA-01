package com.guanqp.java01.week04;

/**
 * @ClassName Work01
 * @Descriptin Object wait notify方法
 * @Author Skippyb
 * @Date 2021/2/6 20:03
 * @Version 1.0
 **/
public class Work01 {

  public static void main(String[] args) {
    Object oo = new Object();
    Obj1 obj1 = new Obj1();
    obj1.setOo(oo);
    obj1.start();
    synchronized (oo){
      try {
        for(int i=0;i<100;i++){
          if(i==20){
            oo.wait(0);
          }
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
    System.out.println("main:"+obj1.getI());
  }


}

class Obj1 extends Thread{

  private Object oo;
  private int i = 0;

  public int getI() {
    return i;
  }


  public void setOo(Object oo) {
    this.oo = oo;
  }


  @Override
  public void  run (){
        synchronized (oo){
          this.i = getNewI(20);
          oo.notify();
        }


  }


  public int getNewI(int i){
    for(int v = 0;v<10;v++){
      i++;

    }
    return i;
  }


}
