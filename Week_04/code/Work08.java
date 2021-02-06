package com.guanqp.java01.week04;

/**
 * @ClassName Work08
 * @Descriptin 轮询（模拟Future） 方法
 * @Author Skippyb
 * @Date 2021/2/6 23:04
 * @Version 1.0
 **/
public class Work08 {

  public static void main(String[] args) {
    ResultObj resultObj=new ResultObj();
    new Thread(resultObj).start();
    while (true){
      if(resultObj.getResult()!=null){
        System.out.println(resultObj.getResult());
        break;
      }
    }
  }


}

class ResultObj extends Thread{

  private Object result;

  public Object getResult() {
    return result;
  }


  @Override
  public void run(){
    result="123";
  }


}
