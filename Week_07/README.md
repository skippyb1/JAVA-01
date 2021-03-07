# Week07作业

## 周三-100W数据插入性能比较

|  插入方式   |  时间 |
|  ----  | ----  |
| jdbc(预编译，手动事务)  | 103.362秒 |
| jdbc(预编译，手动事务,批处理)  | 17.633秒 |
| jdbc(预编译，手动事务,批处理 , 多线程(5线程)) | 11.919秒 |
| mybatis(ExecutorType.SIMPLE)  | 207.851秒 |
| mybatis(ExecutorType.BATCH 批处理)  | 26.29秒 |
| hibernate  | 160.088秒 |
| hibernate(hibernate.jdbc.batch_size批处理)  | 35.551秒 |
| sql文件导入  | 74秒 |

## 周日-读写分离

### 读写分离0.1


### 读写分离0.2
