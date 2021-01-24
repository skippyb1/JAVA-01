# 4.NIO 模型与 Netty 入门

## socket基础

1. java线程真正是jvm从操作系统申请的线程，资源宝贵

## 通信模型

> 阻塞、非阻塞是线程处理模式 同步、异步是通信模式

1. 同步、异步是对调用者同步或异步

### IO模型

1. 阻塞IO模型 同步
    BIO 线程全程阻塞
2. 非阻塞IO模型 同步
    轮询 无数据返回EWOULDBLOCK 有数据内核态复制到用户 ->数据处理
3. IO复用模型 同步 (Reactor模式 添加一个专门人来处理多套接字)
    1. select/poll轮询多套接字fd，内核态复制到用户态损耗过大,fd上限1024
    2. epoll fd上线为10W，通过回调解决遍历问题，内核与用户空间共享一块内存，linux2.6
4. 信号驱动IO模型 同步
    1. 数据准备阶段不会阻塞用户进程
    2. SIGIO handler在数据准备好时通知
    3. 需要操作系统支持
    4. 线程池->EDA->SEDA
5. 异步IO模型 异步
    1. windows的IOCP模型
    2. 全程操作系统异步处理
6. 关于java的网络,有个比喻形象的总结
例子：有一个养鸡的农场，里面养着来自各个农户（Thread）的（Socket）,每家农户都在农场中建立了自己的鸡舍（SocketChannel）
    1. BIO：Block IO，每个农户盯着自己的鸡舍，一旦有鸡下蛋，就去做捡蛋处理；
    2. NIO：No-BlockIO-单Selector，农户们花钱请了一个饲养员（Selector），并告诉饲养员（register）如果哪家的鸡有任何情况  （下蛋）均要向这家农户报告selectkeys）;
    3. NIO：No-BlockIO-多Selector，当农场中的鸡舍(Selector)逐渐增多时，一个饲养员巡视（轮询）一次所需时间就会不断地加长，这样农户知道自己家的鸡有下蛋的情况就会发生较大的延迟。怎么解决呢？没错，多请几个饲养员（多Selector），每个饲养员分配管理鸡舍，这样就可以减轻一个饲养员的工作量，同时农户们可以更快的知晓自己家的鸡是否下蛋了；
    4. Epoll模式：如果采用Epoll方式，农场问题应该如何改进呢？其实就是饲养员不需要再巡视鸡舍，而是听到哪间鸡舍(Selector)的鸡打鸣了（活跃连接），就知道哪家农户的鸡下蛋了；
    5. AIO：AsynchronousI/O,鸡下蛋后，以前的NIO方式要求饲养员通知农户去取蛋，AIO模式出现以后，事情变得更加简单了，取蛋工作由饲养员自己负责，然后取完后，直接通知农户来拿即可，而不需要农户自己到鸡舍去取蛋。
7. 操作系统通信模型

| 通信模型|操作系统|年份|
|  ----  | ----  |----  |
|epoll |linux| 2002|
|kquene | MAC | 2000 |
|iocp | windows | 1993|

### 数据复制

1. 用户态->内核态

### Netty概览

1. 异步
2. 事件驱动
3. 基于NIO
4. 适用于服务端、客户端、TCP/UDP
5. 高吞吐、低延迟、低开销、零拷贝、可扩容、松耦合、维护性好
6. jdk兼容
    1. 3.x：1.5
    2. 4.x：1.6
    3. 5.x：废弃（过度复杂、没有4效率高）
7. 协议兼容性
    1. 大部分通用协议
    2. 支持自定义协议
8. 基本概念
    1. Channel NIO基本概念，代表一个打开的连接，可执行读写IO操作，netty所有Channel都是非阻塞的
    2. ChannelFuture JAVA Future接口，只能查询操作的完成情况，或者阻塞当前线程等待操作完成
    3. Event&Handler Netty基于事件驱动，事件和处理器可以关联到入站或出站数据流
    4. Encoder&Decoder 处理网络IO时，序列号与反序列化，转换Java字节流
        1. 入站解码 ByteToMessageDecoder
        2. 出站编码 MessageToByteEncoder
    5. ChannelPipeline 数据处理管道就是事件处理器链，有顺序、同一Channel的出站入站处理器在同一列
        表里
9. 入站事件
    * 通道激活和停用
    * 读操作事件
    * 异常事件
    * 用户事件
10. 出站事件
    * 打开连接
    * 关闭连接
    * 写入数据
    * 刷新数据
11. 事件处理程序接口
    1. ChannelHandler
    2. ChannelOutBoundHandler
    3. ChannelInBoundHandler
12. 适配器（空实现，需要继承使用）
    1. ChannelInBoundHandlerAdapter
    2. ChannelOutBoundHandlerAdapter
13. Netty应用组成
    1. 网络事件
    2. 应用程序逻辑事件
    3. 事件处理程序
