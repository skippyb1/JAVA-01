package com.guanqp.java01.week04;

/**
 * @ClassName Work01
 * @Descriptin Object wait(2000)方法
 * @Author Skippyb
 * @Date 2021/2/6 20:03
 * @Version 1.0
 **/
public class Work03 {

  public static void main(String[] args) {
    Object oo = new Object();
    Obj3 obj3 = new Obj3();
    obj3.setOo(oo);
    obj3.start();
    synchronized (oo){
      try {
        for (int i = 0; i < 100; i++) {
          if(i==20){
            oo.wait(2000);
          }else{
            System.out.println("main"+i);
          }
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
    System.out.println("main:"+obj3.getI());
  }


}

class Obj3 extends Thread{

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
        }


  }


  public int getNewI(int i){
    for(int v = 0;v<10;v++){
      i++;
      System.out.println("Obj3+"+i);
    }
    return i;
  }


}
