# 3.调优分析与面试经验

## 日志解读

1. youngGC -> minor gc
2. full GC -> major gc(youngGC+oldGC)

### 日志参数

1. -XX:+PrintGCDetails 控制台打印gc详情
2. -XX:+PrintGCDateStamps 控制台打印gc时间戳
3. -Xloggc:xxxx.log gc文件名称

## 内存分析

### OutOfMemoryError: JAVA heap space（oom）

> 创建新的对象时，堆内存的空间不足与存放新创建的对象

#### 产生的原因

> 类似XXL的对象，往S号的 java heap space里塞

1. 超出预期的访问量\数据量
2. 内存泄漏,代码隐蔽问题
3. （OutOfMemoryError:PermGen space\Metaspace）Metaspace内存不足,加载到class数量太多或者体积太大，超过meta区

    * 增大PermGen/Metaspace -XX:MaxPermSize=512m -XX:MaxMetaspaceSize=512
    * 默认Meta大小是无上限，手动设置会出现问题
    * 高版本JVM可以：-XX:+CMSClassUnloadingEnabled
    * 建议手动设置不大不小的值，对手动创建类加载器产生反复类加载问题有提示作用（当meta区大小大于手动设置的值）
4. （OutOfMemoryError:Unable to create new native thread）创建的线程数量已达到上限值的异常信息 ？ 这个上线值是什么的上限值
    * 调整系统参数 ulimit -a，echo 12000>/proc/sys/kernal/threads-max
    * 降低xss等参数
    * 调整代码，改变线程创建的和使用方式
    * [Mac/Linux中XMX/XSS对最大线程数的影响](https://shimo.im/docs/xKyqr3P8R96DYCT6/)

* 解决办法

1. 增加堆内存的大小

## 调优经验

### 如何确定最优堆内存，如何选择垃圾回收器

1. gc暂停时间的情况（STW100~200ms，就可以只关注吞吐量）
2. 吞吐量（根据典型业务场景，模拟测试行为case 下单、结算等，最终的tps/qps）
3. gc延迟对比（zgc~shenandoah<< g1~cms<parallal < serial）
4. 正常情况youngGC多，oldGC少，非常健康情况下youngGC <<oldGC

### 高分配速率

> 分配速率表示单位时间内分配的内存量，通常MB/sec作为单位。上一次gc后，与下一次gc开始之前的young区内存使用量，两者的差值除以时间，就是分配速率

1. 正常系统：分配速率较低~回收速率->健康 分配速率与回收速率大致持平
2. 内存泄漏：分配速率持续大于回收速率->OOM 新对象创建过多 gc来不及
3. 性能劣化：分配速率很高~回收速率-> 亚健康 分配速率和gc速率都很高 gc线程占用

>> 分配速率过高，增加young（Eden）区大小，解决的是延缓gc频率，并不能阻止系统崩溃，应该分担系统压力，增加机器数或优化代码

### 过早提升

> 提升速率用于衡量单位时间内从young提升到old的数据量。MB/sec作为单位与分配速率类似 老年代中不仅有存活时间长的对象，也可能存在时间短的对象，这就是过早提升，会严重影响fgc(major gc)频率,会严重影响系统的吞吐量

#### 过早提升现象

1. 短时间内频繁fullgc
2. 每次fullgc后老年代使用率很低，在10-20%或以下（在老年代也不是长期存活的，适合在young区）
3. 提升速率接近分配速率

* 解决办法

1. 增加young大小
2. 减少大对象和大方法
3. 在不允许减少批处理的数量只能增加堆内存或者重新指定年轻代大小，优化数据结构

> 总体目标：让临时数据能够在年轻代存放的下

## 疑难问题分析

### 分析工具

1. arthas诊断分析工具
2. GCeasy gc日志分析工具
3. fastThread 线程分析工具

## 分析案例

### GCLogAnalysis.java

> 表格数据

#### SerialGC

| GC | 内存大小 | 次数 | youngGC(占用总、平均、最小、最大时间)| oldGC(占用总、平均、最大、最小时间) | 创建对象时间  |创建对象个数  |总时间(秒)|
|  ----  | ----  |----  | ----  |----  |----  |----  |----  |
| SerialGC | 256m | 1 | 0.11s/15.7ms/10ms/20ms | 0.67s/15ms/2ms/40ms | 0.22s | 4470| 1
| SerialGC | 256m | 2 | 0.09s/13ms/10ms/20ms | 0.66s/15ms/3ms/40ms | 0.25s | 4700| 1
| SerialGC | 256m | 3 | 0.09s/13ms/10ms/20ms | 0.62ms/10ms/2ms/40ms | 0.29s | 4896| 1
| SerialGC | 1g | 1 | 0.4s/40ms/10ms/50ms | 0.05s/50ms/50ms/50ms | 0.55s | 11774| 1
| SerialGC | 1g | 2 | 0.47s/46.7ms/10ms/60ms | 0.05s/50ms/50ms/50ms | 0.48s | 10781| 1
| SerialGC | 1g | 3 | 0.38s/42.2ms/10ms/60ms | 0.05s/50ms/50ms/50ms | 0.57s | 11733| 1
| SerialGC | 4g | 1 | 0.3s/150ms/120ms/180ms | n/a | 0.7 | 8352| 1
| SerialGC | 4g | 2 | 0.27s/135ms/130ms/140ms | n/a | 0.63 | 8541| 1
| SerialGC | 4g | 3 | 0.3s/150ms/120ms/180ms | n/a | 0.7 | 8352| 1

#### ParllelGC

| GC | 内存大小 | 次数 | youngGC(占用总、平均、最小、最大时间)| oldGC(占用总、平均、最大、最小时间) | 创建对象时间  |创建对象个数  |总时间(秒)|
|  ----  | ----  |----  | ----  |----  |----  |----  |----  |
| ParllelGC | 256m | 1 | 0.03s/2.31ms/2ms/10ms | 0.64s/16.8ms/2ms/40ms | 0.33s | 崩溃| 1
| ParllelGC | 256m | 2 | 0.06s/4.62ms/2ms/10ms | 0.52s/19.3ms/3ms/30ms | 0.42s | 崩溃| 1
| ParllelGC | 256m | 3 | 0.06s/7.5ms/2ms/10ms | 0.55s/19ms/2ms/40ms | 0.49s | 崩溃| 1
| ParllelGC | 1g | 1 | 0.3s/11.5ms/2ms/20ms | 0.09s/45ms/40ms/50ms | 0.61s | 14946| 1
| ParllelGC | 1g | 2 | 0.29s/11.2ms/2ms/20ms | 0.08s/50ms/50ms/50ms | 0.63s | 15299| 1
| ParllelGC | 1g | 3 | 0.28s/11.7ms/2ms/20ms | 0.08s/40ms/40ms/40ms | 0.64s | 14364| 1
| ParllelGC | 4g | 1 | 0.14s/46.7ms/40ms/60ms | n/a | 0.86 | 12474| 1
| ParllelGC | 4g | 2 | 0.12s/40ms/30ms/50ms | n/a | 0.88 | 11623| 1
| ParllelGC | 4g | 3 | 0.08s/40ms/30ms/50ms | n/a | 0.92 | 11813| 1

#### ParNew+CMS

| GC | 内存大小 | 次数 | youngGC(占用总、平均、最小、最大时间)| oldGC(占用总、平均、最大、最小时间) | 创建对象时间  |创建对象个数  |总时间(秒)|
|  ----  | ----  |----  | ----  |----  |----  |----  |----  |
| ParNew+CMS | 256m | 1 | 0.3s/20ms/2ms/30ms | 0.4s/33.3ms/20ms/30ms | 0.3s | 4104| 1
| ParNew+CMS | 256m | 2 | 0.34s/20ms/2ms/30ms | 0.41s/29.3ms/22ms/50ms | 0.25s | 4305| 1
| ParNew+CMS | 256m | 3 | 0.29s/19.3ms/2ms/30ms | 0.31s/31ms/20ms/40ms | 0.49s | 4390| 1
| ParNew+CMS | 1g | 1 | 0.37s/33.6ms/10ms/50ms | 0.05s/50ms/50ms/50ms | 0.58s | 13562| 1
| ParNew+CMS | 1g | 2 | 0.41s/37.3ms/20ms/50ms | n/a | 0.59s | 12524| 1
| ParNew+CMS | 1g | 3 | 0.35s/29.2ms/10ms/40ms | 0.06s/60ms/60ms/60ms | 0.59s | 14257| 1
| ParNew+CMS | 4g | 1 | 0.28s/56ms/30ms/80ms | n/a | 0.72s | 12474| 1
| ParNew+CMS | 4g | 2 | 0.3s/60ms/30ms/80ms | n/a | 0.7s | 12171| 1
| ParNew+CMS | 4g | 3 | 0.31s/57.1ms/20ms/70ms | n/a | 0.69s | 13479| 1

#### G1

| GC | 内存大小 | 次数 | 年轻GC(占用总、平均、最小、最大时间)| oldGC(占用总、平均、最大、最小时间) | 创建对象时间  |创建对象个数  |总时间(秒)|
|  ----  | ----  |----  | ----  |----  |----  |----  |----  |
| G1 | 256m | 1 | 0.1s/2.44ms/2ms/10ms | 0.18s/10.6ms/2ms/20ms | 0.3s | 4104| 1
| G1 | 1g | 1 | 0.13s/9.29ms/2ms/20ms | n/a | 0.58s | 13441| 1
| G1 | 4g | 1 | 0.21s/16.2ms/10ms/40ms | n/a | 0.79s | 14393| 1

## JVM线程堆栈数据分析

> JVM线程主要包括以下几种

1. VM线程：单例的VMThread对象，负责执行VM操作
2. 定时任务线程：单例的WatcherThread对象，模拟在VM中执行定时操作的计时器中断
3. GC线程：用于垃圾回收，用于支持并行和并发垃圾回收的线程
4. 编译器线程：将字节码编译成本地代码的线程
5. 信号分发线程：等待进程指示的，并将其分配给JAVA级别的信号处理方法
6. 用户线程

## 对象

1. 对象=对象头+对象体+对齐
2. JOL包查看java对象布局
3. 64位JVM，一个对象最少占用16字节，32位占用8字节
4. JVM开启压缩指针会减少内存占用
5. 64位JVM一般需要多消耗内存
6. 包装类型比原生类型消耗内存要多（Integer 16字节 8+4+对齐 Long 16字节 8+8）
7. 多维数组每一个嵌套数组都是一个单独的Object （谨慎使用）
8. String对象有24字节额外开销

