# 2.JVM 核心技术（二）：工具与 GC 策略

## jvm命令行工具

1. java java启动
2. javac 编译
3. javap 反编译
4. javadoc 生成API文档
5. javah java代码生成.h文件 jni开发
6. jar 打包
7. jarsigner jar文件签名
8. jps/jinfo 查看java进程 jps -mlv 详细参数
9. ** jstat 查看JVM内部gc相关信息 -gc -gcutil
10. ** jmap 查看heap或非heap占用空间统计 -heap 打印堆信息       -histo 直方图 -dump *
11. **jstack 查看线程信息
12. jcmd 执行jvm相关分析命令 综合前面几个命令 jcmd xxx help
13. jjs/jrunscript 执行js命令

## jvm图形化工具

1. jconsole
2. jvisualvm
3. ** jmc 飞行记录

## gc（垃圾回收/内存管理器）

### why gc

> 内存资源的有限性

### what is gc

> 对象的生命周期管理

### STW(STOP THE WORLD)

> 标记和清除上百万对象，让全世界停下来，避免对象关系变更，导致gc暂停，jvm停止工作

### gc策略

1. 年轻代GC -> youngGC
2. 老年代GC -> oldGC

### 分代gc

> 分代假设，大部分对象很快无用，存活较长的对象，可能存活更长时间，所以分成年轻代和老年代

1. young（年轻代），包含eden | s0 | s1,默认占用Xmn比例 8：1：1
    1. eden区绝大部分对象在youngGC后会被清除，剩下的对象会放置到存活区 s0 s1中
    2. s0 s1 -- from to 两者会来回交换 总有一个是空的
    3. youngGC 标记阶段 eden区 和 from区把存活的对象复制到to区，然后把eden区 和 from区清空from区转换为to区，to区转换为from区，以便与下次youngGC
    4. -XX:+MaxTenuringThreshold=15 存活区存活周期阈值默认为15次后，提升到老年代
    5. eden 区 到存活区 是复制，而非移动
2. old （老年代）
    1. 默认都是存活对象，采用移动方式（老年代只有一个区）
    2. 标记所有通过GC roots可达的对象
    3. 删除不可达对象
    4. 整理老年代空间，方法是将所有存活对象复制，从老年代空间开始的地方依次存放

### gc算法

> 标记过程暂停时间跟存活对象数量有关系，所有增加堆内存大小不会直接影响标记阶段占用的时间

1. 标记清除算法（old区）
    1. Marking（标记）：遍历可达对象，在本地内存（native）中分门别类记下
    2. Sweeping（清除）：不可达的对象占用的内存，在进行内存分配后可以重用
    3. 并行GC和CMS（C 并行 M 标记 S清除）基本原理
    4. 可以处理循环依赖，只扫描部分对象
2. 标记复制算法 （young区）

3. 标记清除整理算法（old区）

4. GC ROOT对象
    1. 正在执行方法的局部变量，输入参数
    2. 活动线程
    3. 所有类静态对象
    4. JNI引用

### gc种类

#### 串行gc（Serial GC）

1. -XX:+UseSerialGC
    1. 年轻代使用标记复制算法，老年代使用标记清除整理算法
    2. 单线程，不能并行，只要GC触发全线暂停（STW）
    3. 垃圾收集只使用单核，多核无效
    4. CPU利用率高，暂停时间长，简单粗暴
    5. 只适合几百MB堆内存的JVM，而且单核CPU时比较有用

#### 并行gc（Parallel GC）

1. -XX:+UseParllelGC
    1. -XX:+UseParllelGC = -XX:+UseParllelOldGC = -XX:+UseParllelGC -XX:            +UseParllelOldGC
    2. 年轻代与老年代都会触发STW
    3. 年轻代使用标记复制算法，老年代使用标记清除整理算法
    4. 使用多核，增加吞吐量
    5. GC 期间，所有CPU内核都在并行清理垃圾，所以总暂停时间更短，吞吐量高，效率高
    6. 两次GC周期的间隔期，没有GC线程运行，不会消耗任务系统资源
    7. -XX：ParallerGCThreads=N 指定GC线程数，cpu核数小于等于8 ，则默认为cpu核数数量，大
        于8个，核数*5/8+3

#### 并发gc（CMS GC）

1. -XX:+UseConcMarkSweepGC
    1. 年轻代使用并行STW方式的标记复制算法，老年代使用并发标记清除算法
    2. 并发GC表示gc线程与业务线程同时工作
    3. 不对老年代整理，而使用空闲列表（中间层索引）管理内存空间碎片
    4. 老年代GC时与业务线程争抢CPu时间，默认情况下，并发线程等于CPU核心数的1/4
    5. 优化GC停顿导致的系统延迟，吞吐量低
    
2. 六个阶段
    1. 初始标记
        > 该阶段伴随STW暂停，初始标记的目标是标记所有根对象，包括根对象的引用对象，以及被
            年轻代中所有存活对象中所引用的对象（老年代单独回收）
    2. 并发标记
        > 此阶段，CMS GC遍历老年代，标记所有存活对象，从前一阶段初始标记找到的根对象开始算
          起，与业务程序同时运行，不用暂停
    3. 并发预清理
        > 将并发标记阶段发生引用关系变化的区域标记为脏区（脏卡），不用暂停
    4. 最终标记
        > STW暂停，完成所有存活对象的标记，尝试在年轻代尽可能空的状态执行该阶段，避免多次STW
    5. 并发清除
        > 清除不可达对象,不用暂停
    6. 并发重置
        > 回收删除后的对象所占用的空间

3. -XX:+UseParNewGC
    1. 改进版的Serial GC，配合CMS使用

4. 优缺点
    1. STW时间暂停短
    2. 步骤复杂，效率低，频率高，空间碎片严重，因为不压缩，不整理
    3. 在某些情况下gc会造成不可预测的暂停时间，特别是内存较大的情况

### G1 GC（分区gc）

1. -XX:+UseG1GC

    1. 垃圾优先，哪块垃圾最多就优先处理
    2. 堆不在分为young区和old区，而是划分小快堆区（一般2048个），每个小块可能是Eden、
       Survivor、old块
    3. 逻辑上所有Eden、Survivor块加起来就是young区，所有old块就是old区
    4. 回收原则：垃圾最多的小块会被优先收集
    5. 参数：
        1. -XX：G1NewSizePercent 初始年轻代占整个堆的大小，默认5%
        2. -XX：MaxG1NewSizePercent 最大轻代占整个堆的大小，默认60%
        3. -XX: G1HeapRegionSize: 设置每个Region的大小MB为单位，需要2的整次幂1，2，4，
                8，16，默认是堆内存的1/2000，如果值过大，大对象就可以进入Region了
        4. -XX: ConcGCThreads gc线程数，默认是Java线程的1/4，数值过低导致gc回收机制耗时
                过长
        5. -XX: +InitiatingHeapOccupancyPercent G1内部并行回收循环启动的阈值，默认为堆
                的45%，老年代使用率大于等于45%的时候，决定了什么时候启动老年代的并行回收
        6. -XX: G1HeapWastePercent G1停止回收的最小内存。默认5%
        7. -XX: +GCTimeRatio 计算业务应用线程与GC线程花费时间的比例，默认是9，公式为（100/1+GCTimeRatio）,也就是说gc线程花费时间默认为10%，Parallel GC默认为99
        8. -XX:MaxGCPauseMills=50(默认200) 期望gc时间为xx：ms

2. G1处理步骤

3. G1退化Serial GC（某些情况下G1 FullGC后会导致退化为Serial GC）

    1. 并发模式失败，G1启动标记周期，但在MixGC之前，老年代满了，会放弃标记周期，解决办法增加堆大小，或增加线程数
    2. 晋升失败，没有内存供存活对象或晋升对象使用，解决办法
       1. -XX：G1ReservePercent（并相应增加堆大小）增加预留内存量
       2. 通过减少-XX: InitiatingHeapOccupancyPercent提前启动标记周期
       3. 增加线程数
    3. 巨型对象分配失败，解决办法
       1. 内存增大或Region块增大

### GC总结

|  收集器   | 串、并行、并发 |   新/老   | 算法  | 目标  |
|  ----  | ----  |----  | ----  |----  |
| Serial  | 串 | 新 | 复制 |响应优先  |
| SerialOld  | 串   | 老 | 标记-整理 | 响应优先 |
|  ParNew | 并行  | 新 | 复制 | 响应优先 |
| ParallelScavenge  | 并行 | 新 | 复制 | 吞吐量 |
| Parallel Old  | 并行 | 老 | 标记整理  | 吞吐量 |
| CMS  | 并发 | 老  | 标记清除 | 响应优先 |
| G1  | 并发 | both | 标记整理+复制 | 响应优先 |

1. GC推荐组合
    1. Serial+SerialOld 单线程低延迟
    2. ParallelScavenge+Parallel Old 多线程高吞吐
    3. ParNew+CMS 并发 多线程低延迟
    4. G1 分区 大内存低延迟
    5. 4g以上内存推荐G1 性价比比较高
    6. 超过8g 16-64g 非常推荐G1
    7. 128g以上 zgc（Oracle） shenandoah（RedHat）  吞吐量会低

2. 未来GC

    1. zgc

    * Mac Windows jdk15
    * Linux jdk11
    * -XX：+UnlockExperimentalVMOptions -XX:+UseZGC -Xmx16g
    * gc停顿不超过10ms，与G1相比吞吐下降不超过15%
    * 通过着色指针和读屏障

    2. shenandoah

    * Mac Windows jdk15
    * Linux jdk11
    * -XX：+UnlockExperimentalVMOptions -XX:+UseShenandoah -Xmx16g
    * gc停顿不超过10ms，与G1相比吞吐下降不超过15%

3. 并行默认配置
    1. MaxHeapSize 堆最大默认大于1g 1/4 小于等于1g 1/2
    2. MaxNewSize young区最大 1/3
    3. old区最大2/3
    4. 默认自适应参数开启 -XX:-UseAdaptiveSizePolicy 关闭
    5. Young区容量大小为当前容量大小,不是最大

4. CMS并发默认配置
    1. young区计算最大值 64M * 并发GC线程数 * 13 / 10,jvm写死,与并发gc线程数
    -XX:ParallelGCThreads有关
    2. -XX:ConcGCThreads=m 指定old区的cms线程数,默认为(ParallelGCThreads + 3)/4 向下去整
