package com.guanqp.java01.week04;

/**
 * @ClassName Work02
 * @Descriptin Thread join方法
 * @Author Skippyb
 * @Date 2021/2/6 20:03
 * @Version 1.0
 **/
public class Work02 {

  public static void main(String[] args) {
    Obj2 obj2 = new Obj2();
    obj2.start();
    synchronized (obj2){
      try {
        for(int i=0;i<100;i++){
          if(i==20){
            obj2.join();
          }else{
            System.out.println("main"+i);
          }
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
    System.out.println("main:"+obj2.getI());
  }


}

class Obj2 extends Thread{

  private int i = 0;

  public int getI() {
    return i;
  }


  @Override
  public void  run (){
    synchronized (this){
      this.i = getNewI(20);
    }

  }


  public int getNewI(int i){
    for(int v = 0;v<10;v++){
      i++;
      System.out.println("Obj2+"+i);
    }
    return i;
  }


}
