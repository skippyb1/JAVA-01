# 1.JVM 核心技术（一）：基础知识

> java面向对象、静态类型、编译执行、有Runtime运行时、有虚拟机、有gc、跨平台

## java字节码

### 字节码基础

1. java bytecode 由单字节的指令组成，理论最多256,实际只使用200左右，还有一部分保留给调试操作
2. cafe babe 魔数 class文件开头 用作jvm虚拟机判断文件是否为class文件，以及文件是否受损 
3. jvm基于栈运行的
4. jvm栈一个槽位可以占用32位数据，double和long占用2个槽位

### 字节码类型（助记符）

> 主要包括4大类 [字节码对照表](https://www.cnblogs.com/tsvico/p/12708417.html)

1. 栈操作指令，包括与局部变量交互的指令
    * load 将本地变量表中槽位为指定数字的类型变量（l，d，i，f,[byte作为int处理]，a[引用类型]）推入栈顶
    * store 将类型变量存入到本地变量表指定槽位
    * const 将类型常量推入栈顶
    * dup_x1 复制栈顶的值, 并将复制的值插入到最上面2个值的下方
        > ..., value2, value1 →
          ..., [value1,] value2, value1
    * dup_x2 复制栈顶 1个64位/或2个32位的值, 并将复制的值按照原始顺序，插入原
      始值下面一个32位值的下方
        >  情景1: value1, value2, and value3都是分组1的值(32位元素)
        ..., value3, value2, value1 →
        ..., [value2, value1,] value3, value2, value1
        >>  情景2: value1 是分组2的值(64位,long或double), value2 是分组1的值(32位元素)
            ..., value2, value1 →
            ..., [value1,] value2, value1
2. 程序流程控制指令
    * ifne 42 判断本地变量表槽位4，2的是否不相等
3. 对象操作指令，包括方法调用指令
    * invokestatic 静态方法调用
    * invokespecial 构造、本地私有、超类方法
    * invokevirtual 如果具体类型目标对象，调用public 、protected、package
    * invokeinterface 接口引用方法调用
    * invokedynamic lambda表达式实现 方法签名相同调用Function
    * getfield 堆上获取类实例
    * setfield 为堆上类实例赋值
4. 算数运算以及类型转换指令
    * iadd int类型相加
    * i2d int转换为double

### 命令

1. javac 编译java文件生成class文件包含字节码 -g 生成所有调试信息（本地变量表 -verbose需要该命令）
2. javap 反编译命令 -c class文件进行反编译 -verbose 输出反编译详细信息（本地变量表等）

### 反编译信息

1. LineNumberTable 源代码行号与指令对照表
2. LocalVariableTable 方法本地变量表 Start-Length 二进制位置变量范围（开始-长度） slot槽位
3. 无参构造参数参数个数为1 ，其实就是this
4. 方法描述 locals 为方法本地变量表变量个数 stack 栈长度 arg_size 方法参数个数

* 0：new #2 0为二进制位置  new为助记符 #2 为常量池位置

## java类基础

1. 类的生命周期（前五步为类加载）
    1. 加载：找class文件
    2. 验证：验证格式、依赖
    3. 准备：静态字段、方法表
    4. 解析：符号解析为引用
    5. 初始化：构造器、静态变量复制、静态代码块
    6. 使用
    7. 卸载

2. 类的加载时机
    1. 初始化主类，main方法所在的类
    2. new
    3. 调用静态方法
    4. 访问静态字段
    5. 子类初始化触发父类初始化
    6. 接口default，直接实现、间接实现都会实现该接口的初始化
    7. 反射
    8. 初次调用MethodHandle实例，初始化该MethodHandle指向的方法所在的类

3. 类不会初始化（可能会加载）
    1. 子类引用父类静态字段，子类不会初始化
    2. 定义对象数组
    3. 常量在编译期间会存入调用类的常量池中
    4. 通过类名获取Class对象
    5. Class.forName initialize参数为false时
    6. ClassLoader loadClass方法
4. 类的加载器(溯源双亲委托、负责依赖、缓存加载)
    1. BootstrapClassLoader
        > String类的类加载器为null，因为是BootstrapClassLoader
    2. URLClassLoader
        > 通过URL加载类（jdk9以下可以把ExtClassLoader、AppClassLoader转换为URLClassLoader，jdk9 URLClassLoader与ExtClassLoader、AppClassLoader 平级）
    3. ExtClassLoader
    4. AppClassLoader
        > 默认启动程序类加载器
    5. 类只加载一次
5. 添加引用类的方法
    1. jdk的lib/ext下，-Djava.ext.dirs
    2. java -cp/classpath获取class文件放到当前路径
    3. 自定义ClassLoader加载
    4. 当前类ClassLoader，反射addUrl方法添加jar类型（jdk9无效）
        > Class.forName("xxx",new URLClassLoader("jar文件路径")) jdk9

## jvm基础

### jvm内存结构

1. jvm内存结构详情
    1. 栈内存
    2. 堆内存
    3. 非堆内存
    4. 堆外内存
    5. jvm自身内存
2. 栈特征
    1. 线程只能访问自己的线程栈
    2. 线程不能访问其他线程的局部变量
    3. 所有原生类型的局部变量存储在线程栈中，对其他线程不可见
    4. 线程可以将原生变量的副本传给其他线程，但不能共享原生变量本身
    5. 堆内存包含Java代码中创建的所有对象（Byte，Integer，Long）
    6. 所有创建的对象都存储在堆上
    7. 方法中原生数据类型、对象引用地址在栈上存储，对象和对象成员、类定义、静态变量在堆上
    8. 堆的所有对象，可以被所有线程访问，只要能获取到对象引用地址
    9. 线程可以访问对象，也就可以访问对象的成员变量
    10. 线程访问的是对象副本（线程安全问题）
    11. 每执行一个方法就会创建对应的栈帧
    12. 线程栈也叫java方法栈，jni方法会创建本地方法栈
3. 栈内存结构
    1. 返回值
    2. 局部变量表
    3. 操作数栈
    4. Class/Method指针（表示这个栈帧对应是哪个类的方法，指向非堆的Class对象）
4. 堆内存结构
    1. Young
        1. Eden(Eden space)
            1. TLAB 专门给线程的缓冲区 ，线程的局部变量可以存放在TLAB区
        2. S0 S1(Survivor space | from to | 总有一个是空的)
    2. Old

5. 非堆结构（jdk8）
    1. Metaspace(PermSpace) 常量池/方法区
    2. CSS 存放class信息与Metaspace有交叉 方法指针
    3. code cache 存放本地机器代码
6. jvm启动参数类型
    1. 系统属性参数
    2. 运行模式参数
    3. 堆内存设置参数
    4. gc设置参数
    5. 分析诊断参数
    6. JavaAgent参数

7. jvm启动参数前缀类型
    1. -开头为标准参数
    2. -D设置系统属性（system传参）
    3. -X为非标准参数 java -X查看当前JVM支持的标准参数
    4. -XX为非稳定参数，专门控制JVM的行为
    5. -XX：+- 对布尔值进行开关
    6. -XX：key=value，指定某个选项的值

8. jvm启动参数运行模式
    1. -server 服务器模式 启动慢
    2. -client 客户端模式 启动快
    3. -Xint 解释模式 运行速度降低
    4. -Xcomp 编译模式 字节码编译成本地代码
    5. -Xmixed 混合模式 默认模式

9. 堆内存参数
    1. -Xmx 最大堆参数
    2. -Xms 堆初始内存大小
    3. -Xmn young区内存大小
    4. -XX:MaxPermSize 最大持久代内存（jdk7之前）
    5. -XX:MaxMetaspaceSize 最大meta区内存，jdk8默认无限大，一般不允许设置该选项
    6. -XX:MaxDirestMemorySize 最大堆外（直接内存）内存，默认跟堆一样大
    7. -Xss 线程栈内存大小
    8. 总结
        > old最大内存=xmx-xmn
        > xmx=xms 好处避免gc后，堆区动态扩展内存带来的压力
        > xmx 推荐设为系统可用内存0.6~0.8
10. gc参数
    1. -XX:+UseG1GC G1
    2. -XX:+UseConcMarkSweepGC CMS
    3. -XX:+UseSerialGC 串行
    4. -XX:+UseParallelGC 并行
    5. -XX:+UnlockExperimentalVMOptions -XX:UseZGC java11+
    6. -XX:+UnlockExperimentalVMOptions -XX:UseShenandoahGC java12+
    7. 总结
        > jdk8及以下 Parallel并行GC为默认GC
        > jdk9及以上 G1并发GC为默认GC
11. 分析诊断分析
    1. -XX: +-HeapDumpOnOutOfMemoryError 自动Dump内存，即内存溢出
    2. -XX: HeapDumpPath dump文件目录 与1配合使用
    3. -XX: OnError 发生致命错误的脚本
    4. -XX：OnOutOfMemoryError 选项, 抛出 OutOfMemoryError 错误时执行的脚本。
    5. -XX：ErrorFile=filename 选项, 致命错误的日志文件名,绝对路径或者相对路径。
    6. -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1506，远程调试
