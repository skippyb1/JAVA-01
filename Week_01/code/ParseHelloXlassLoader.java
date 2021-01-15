package com.guanqp.java01.week01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ParseHelloXlass
 * @Descriptin TODO
 * @Author Skippyb
 * @Date 2021/1/11 20:50
 * @Version 1.0
 **/
public class ParseHelloXlassLoader extends ClassLoader{

  private String xlassPath;

  public ParseHelloXlassLoader(String xlassPath){
    this.xlassPath = xlassPath;
  }


  public static void main(String[] args) throws IOException {

    try {
      Class<?> aClass = new ParseHelloXlassLoader("hello.xlass").findClass("Hello");
      Object o = aClass.newInstance();
      Method hello = aClass.getMethod("hello", null);
      hello.invoke(o,null);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  @Override
  public Class<?> findClass(String className) throws ClassNotFoundException{

    URL resourceURL = ParseHelloXlassLoader.class.getResource(xlassPath);

    if (resourceURL == null) {
      throw new ClassNotFoundException();
    }

    File file = new File(resourceURL.getPath());

    if (!file.exists()) {

      throw new ClassNotFoundException();
    }

    Long length = file.length();

    byte[] b = new byte[length.intValue()];

    byte[] classByte = new byte[length.intValue()];

    try (FileInputStream fileInputStream = new FileInputStream(file)){

      fileInputStream.read(b);

      for (int i = 0; i < b.length; i++) {

        classByte[i] = (byte)(255 - b[i]);

      }

    } catch (IOException e){

      e.printStackTrace();

    }

    return defineClass(className,classByte,0,classByte.length);
  }


}
