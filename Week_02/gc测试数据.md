# 分析数据

## GCLogAnalysis.java

> 表格数据

### SerialGC

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

### ParllelGC

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

### ParNew+CMS

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


### G1

| GC | 内存大小 | 次数 | 年轻GC(占用总、平均、最小、最大时间)| oldGC(占用总、平均、最大、最小时间) | 创建对象时间  |创建对象个数  |总时间(秒)|
|  ----  | ----  |----  | ----  |----  |----  |----  |----  |
| G1 | 256m | 1 | 0.1s/2.44ms/2ms/10ms | 0.18s/10.6ms/2ms/20ms | 0.3s | 4104| 1
| G1 | 1g | 1 | 0.13s/9.29ms/2ms/20ms | n/a | 0.58s | 13441| 1
| G1 | 4g | 1 | 0.21s/16.2ms/10ms/40ms | n/a | 0.79s | 14393| 1