# week08作业

## 周三 实现数据库水平拆分

1. 订单表 划分为2个库 每个库16张表
2. 用户表和商品表未划分
3. 遇到的问题
    1. 配置yaml属性使用下划线报错（spring2.0以上）
    2. 水平拆分分布式序列算法需要指定算法
    3. spring.shardingsphere.datasource.common 需要定义

## 周日 基于ShardingSphere 的 Atomikos XA实现的分布式事务

1. 根据订单表insert后，抛出异常测试分布式事务是否生效