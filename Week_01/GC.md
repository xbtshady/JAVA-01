## 串行gc
```
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseSerialGC -jar gateway-server-0.0.1-SNAPSHOT.jar
```
jmap -heap详情
```
Attaching to process ID 5924, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.201-b09

using thread-local object allocation.
Mark Sweep Compact GC

Heap Configuration:
   MinHeapFreeRatio         = 40 //最小空闲堆 默认40%
   MaxHeapFreeRatio         = 70 //最大空闲堆 默认70%
   MaxHeapSize              = 1073741824 (1024.0MB) //最大堆内存 
   NewSize                  = 357892096 (341.3125MB) //年轻代内存 1/3最大堆内存
   MaxNewSize               = 357892096 (341.3125MB) //最大年轻代内存 1/3最大堆内存
   OldSize                  = 715849728 (682.6875MB) //老年代内存 2/3最大堆内存
   NewRatio                 = 2 //老年代内存:年轻代内存 = 2:1
   SurvivorRatio            = 8 // 新生代:S0:S1 = 8:1:1
   MetaspaceSize            = 21807104 (20.796875MB) //元数据区内存大小 -XX:MetaspaceSize 
   CompressedClassSpaceSize = 1073741824 (1024.0MB) //亚索类空间大小
   MaxMetaspaceSize         = 17592186044415 MB //最大元数据内存大小
   G1HeapRegionSize         = 0 (0.0MB) // 使用G1时会分成多个Region 设置每个Region大小

Heap Usage:
New Generation (Eden + 1 Survivor Space):
   capacity = 322109440 (307.1875MB)
   used     = 152326288 (145.26966857910156MB)
   free     = 169783152 (161.91783142089844MB)
   47.29022781822228% used
Eden Space:
   capacity = 286326784 (273.0625MB)
   used     = 135732888 (129.4449691772461MB)
   free     = 150593896 (143.6175308227539MB)
   47.404886858226995% used
From Space:
   capacity = 35782656 (34.125MB)
   used     = 16593400 (15.824699401855469MB)
   free     = 19189256 (18.30030059814453MB)
   46.372745499942766% used
To Space:
   capacity = 35782656 (34.125MB)
   used     = 0 (0.0MB)
   free     = 35782656 (34.125MB)
   0.0% used
tenured generation:
   capacity = 715849728 (682.6875MB)
   used     = 14156208 (13.500411987304688MB)
   free     = 701693520 (669.1870880126953MB)
   1.9775390625% used

16002 interned Strings occupying 2152928 bytes.
```
## 并行gc
```
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -jar gateway-server-0.0.1-SNAPSHOT.jar
```
jmap -heap详情
```
Attaching to process ID 7148, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.201-b09

using thread-local object allocation.
Parallel GC with 6 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 1073741824 (1024.0MB)
   NewSize                  = 357564416 (341.0MB)
   MaxNewSize               = 357564416 (341.0MB)
   OldSize                  = 716177408 (683.0MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 268435456 (256.0MB)
   used     = 161760344 (154.26668548583984MB)
   free     = 106675112 (101.73331451416016MB)
   60.26042401790619% used
From Space:
   capacity = 44564480 (42.5MB)
   used     = 16323848 (15.567634582519531MB)
   free     = 28240632 (26.93236541748047MB)
   36.62972842945772% used
To Space:
   capacity = 44564480 (42.5MB)
   used     = 0 (0.0MB)
   free     = 44564480 (42.5MB)
   0.0% used
PS Old Generation
   capacity = 716177408 (683.0MB)
   used     = 5755984 (5.4893341064453125MB)
   free     = 710421424 (677.5106658935547MB)
   0.8037092395966783% used

15887 interned Strings occupying 2123504 bytes.
```
内存默认分配和串行gc不一样的是 下半部分的Eden Space、From Space、To Space内存大小不一样，是因为jmap -heap显示的容量是当前容量，ratio的比例是最大容量,Eden区还没到达最大容量

## cms并发GC
```
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar
```
jmap详情
```
Attaching to process ID 21168, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.201-b09

using parallel threads in the new generation.
using thread-local object allocation.
Concurrent Mark-Sweep GC

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 1073741824 (1024.0MB)
   NewSize                  = 357892096 (341.3125MB)
   MaxNewSize               = 357892096 (341.3125MB)
   OldSize                  = 715849728 (682.6875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
New Generation (Eden + 1 Survivor Space):
   capacity = 322109440 (307.1875MB)
   used     = 157278352 (149.99232482910156MB)
   free     = 164831088 (157.19517517089844MB)
   48.827613372647505% used
Eden Space:
   capacity = 286326784 (273.0625MB)
   used     = 132705792 (126.55810546875MB)
   free     = 153620992 (146.50439453125MB)
   46.34766966124971% used
From Space:
   capacity = 35782656 (34.125MB)
   used     = 24572560 (23.434219360351562MB)
   free     = 11210096 (10.690780639648438MB)
   68.6717050852793% used
To Space:
   capacity = 35782656 (34.125MB)
   used     = 0 (0.0MB)
   free     = 35782656 (34.125MB)
   0.0% used
concurrent mark-sweep generation:
   capacity = 715849728 (682.6875MB)
   used     = 0 (0.0MB)
   free     = 715849728 (682.6875MB)
   0.0% used

16088 interned Strings occupying 2160168 bytes.
```
cms特殊的地方在于最大young区内存为 64M * GC线程数 * 13 / 10

## g1 并发GC
```
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -jar gateway-server-0.0.1-SNAPSHOT.jar
```
jmap -heap详情

```
Attaching to process ID 12264, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.201-b09

using thread-local object allocation.
Garbage-First (G1) GC with 6 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 1073741824 (1024.0MB)
   NewSize                  = 1363144 (1.2999954223632812MB)
   MaxNewSize               = 643825664 (614.0MB)
   OldSize                  = 5452592 (5.1999969482421875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 1048576 (1.0MB)

Heap Usage:
G1 Heap:
   regions  = 1024
   capacity = 1073741824 (1024.0MB)
   used     = 53477360 (50.99998474121094MB)
   free     = 1020264464 (973.0000152587891MB)
   4.980467259883881% used
G1 Young Generation:
Eden Space:
   regions  = 27
   capacity = 205520896 (196.0MB)
   used     = 28311552 (27.0MB)
   free     = 177209344 (169.0MB)
   13.775510204081632% used
Survivor Space:
   regions  = 24
   capacity = 25165824 (24.0MB)
   used     = 25165824 (24.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation:
   regions  = 0
   capacity = 843055104 (804.0MB)
   used     = 0 (0.0MB)
   free     = 843055104 (804.0MB)
   0.0% used

16103 interned Strings occupying 2161664 bytes.
```
因为G1不会进行分代，而是分regions所以young区、old区的内存和别的都不一样

使用superbenchmarker进行压测 
```
sb -u http://localhost:8088 -n 1000
```
使用jstat -gcutil 查看内存情况
```
 S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00  12.00   0.00  95.25  92.77      7    0.043     0    0.000    0.043
  0.00 100.00   0.94   4.08  95.55  94.01     10    0.062     0    0.000    0.062
  0.00 100.00   0.94   4.08  95.55  94.01     10    0.062     0    0.000    0.062
  0.00 100.00   0.94   4.08  95.55  94.01     10    0.062     0    0.000    0.062
  0.00 100.00   2.04   4.08  95.55  94.01     10    0.062     0    0.000    0.062
  0.00 100.00   9.09   4.08  95.55  94.01     10    0.062     0    0.000    0.062
  0.00 100.00   9.09   4.08  95.55  94.01     10    0.062     0    0.000    0.062
  0.00 100.00   9.09   4.08  95.55  94.01     10    0.062     0    0.000    0.062
```
压测命令发送之后从jstat中可以看到进行了10-7=3次YGC,平均耗时为0.062s 