package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("s_user_info")
public class User implements Serializable {
  @TableId(type = IdType.ASSIGN_ID)
  private long id;
  private String username;
  private String password;
  private String login;
  private long status;
  private String idno;
  private long age;
  private long sex;
  private long phone;
  private Date createTime = new Date();
  private Date updateTime = new Date();




}
