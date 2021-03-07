package com.guanqp.java01.week07.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * @author gqp
 * @version 1.0
 * @className MysqlFileGenerate
 * @descriptin TODO
 * @date 2021/3/7 19:47
 **/
public class MysqlFileGenerate {

  public static void main(String[] args) throws Exception {
    String path="D://insert.sql";
    File file=new File(path);
    if(!file.exists()){
      file.createNewFile();
    }

    FileWriter fileInputStream=new FileWriter(file);
    fileInputStream.write("SET NAMES utf8mb4;\r\n" +
        "SET FOREIGN_KEY_CHECKS = 0;\n\n");
    fileInputStream.write("DROP TABLE IF EXISTS `s_order_info`;\n\n" +
        "CREATE TABLE `s_order_info`  (\n\n" +
        "  `id` bigint(20) NOT NULL,\n\n" +
        "  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',\n\n" +
        "  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',\n\n" +
        "  `total_amount` decimal(16, 2) NULL DEFAULT NULL COMMENT '总价',\n\n" +
        "  `pay_price` decimal(16, 2) NULL DEFAULT NULL COMMENT '实付款',\n\n" +
        "  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',\n\n" +
        "  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',\n\n" +
        "  PRIMARY KEY (`id`) USING BTREE\n\n" +
        ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;\n\n"
        );
    for(int i=1;i<=1000000;i++){
      fileInputStream.write(
          String.format("insert into s_order_info values(%s,%s,%s,%s,%s,now(),now());\r\n",
          i,i,i,100,100));
    }
    fileInputStream.write("SET FOREIGN_KEY_CHECKS = 1;");
    fileInputStream.close();
  }

}
