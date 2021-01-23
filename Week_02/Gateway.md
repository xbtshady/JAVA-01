wrk命令
==== 

```
wrk -c40 -d30s http://localhost:8088/api/hello
```

SerialGC
==== 
命令：
 ```
java -Xms512m -Xms512m -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms1g -Xms1g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms2g -Xms2g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms4g -Xms4g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms8g -Xms8g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar

```
>压测结果：
>
 | 内存 |         Requests/sec   |       Avg Latency|        Max Latency |   Avg Req/Sec   | 
 | :----:|:----: |:----: |:----: |:----: |
 | 512m | 17179.91            | 2.54ms            | 151.38ms           | 8.67k           |
 | 1g   | 19991.48           | 2.15ms            | 117.17ms          | 9.94k             |
 | 2g   | 19701.31           | 2.23ms            | 124.49ms         | 9.94k               |
 | 4g   | 19520.52           | 2.66ms            | 205.16ms          | 9.85k             |
 | 8g   | 19945.56           | 2.48ms         | 199.66ms          | 10.08k             |

>GC结果：
>	
| 内存 | youngGC次数 | youngGC总耗时 | FullGC次数 | FullGC总耗时 |
| ---- | --------------- | ----------------- | -------------- | ---------------- |
| 512m |  98              | 290 ms             | 0            | 0ms             |
| 1g   |  56              | 250 ms           | 0           | 0ms           |
| 2g   |  28              | 360 ms             | 0              | 0ms              |
| 4g   |  15              | 340 ms             | 0              | 0ms              |
| 8g   |  8              | 210 ms             | 0              | 0ms              |

>总结：
>
>从512M换到1gQPS获得提升，1-4g的内存增大对QPS没有明确的影响，最大的延迟却增加了，到了8g内存QPS反而减小了.
>
>从GC的角度来看，从512M换到1gGC次数和减少了,后面继续增大内存GC次数减少了但是总耗时却没有明显减少，也就是说GC的平均耗时增加了
                 
 
ParallelGC
==== 
命令：
 ```
java -Xms512m -Xms512m -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseParallelGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms1g -Xms1g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseParallelGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms2g -Xms2g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseParallelGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms4g -Xms4g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseParallelGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms8g -Xms8g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseParallelGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar

```
压测结果：

 | 内存 |         Requests/sec   |       Avg Latency|        Max Latency |   Avg Req/Sec   | 
 | :----:|:----: |:----: |:----: |:----: |
 | 512m | 19984.06            | 2.06ms            | 125.25ms           | 10.10k           |
 | 1g   | 19440.25           |  2.16ms            | 128.41ms          | 9.81k              |
 | 2g   | 20095.06           | 2.02ms            | 121.19ms         | 10.13k               |
 | 4g   | 20428.50           | 2.19ms            | 174.08ms          | 10.32k             |
 | 8g   | 20000.57           | 2.28ms         | 172.12ms          | 10.10k             |
 
 >GC结果：
 >	
 | 内存 | youngGC次数 | youngGC总耗时 | FullGC次数 | FullGC总耗时 |
 | ---- | --------------- | ----------------- | -------------- | ---------------- |
 | 512m |  64              | 40.0 ms             | 0            | 0ms             |
 | 1g   |  42              | 60.0 ms           | 0           | 0ms           |
 | 2g   |  25              | 60.0 ms             | 0              | 0ms              |
 | 4g   |  14              | 80.0 ms             | 0              | 0ms              |
 | 8g   |  8              | 80.0 ms             | 0              | 0ms              |


>总结：
>
>QPS没有因为内存的变化获得太大的变化，内存超过4g之后延迟增加了
>youngGC次数随着内存的增大次数减少，总耗时增加，平均耗时增加

ConcMarkSweepGC
==== 
命令：
 ```
java -Xms512m -Xms512m -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms1g -Xms1g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms2g -Xms2g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms4g -Xms4g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms8g -Xms8g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar

```
压测结果：

 | 内存 |         Requests/sec   |       Avg Latency|        Max Latency |   Avg Req/Sec   | 
 | :----:|:----: |:----: |:----: |:----: |
 | 512m | 19761.16            | 2.16ms            | 137.59ms           | 9.97k           |
 | 1g   | 20114.35           |  2.15ms            | 147.66ms          | 10.16k              |
 | 2g   | 19916.28           | 2.20ms            | 146.76ms         | 10.06k                |
 | 4g   | 20665.90           | 2.14ms            | 145.85ms          | 10.44k            |
 | 8g   | 20103.29           | 2.34ms         | 160.34ms          | 10.16k             |
 
  >GC结果：
  >	
  | 内存 | youngGC次数 | youngGC总耗时 | FullGC次数 | FullGC总耗时 |
  | ---- | --------------- | ----------------- | -------------- | ---------------- |
  | 512m |  39              | 90.0 ms             | 0            | 0ms             |
  | 1g   |  40              | 70.0 ms           | 0           | 0ms           |
  | 2g   |  39              |110 ms             | 0              | 0ms              |
  | 4g   |  41              | 430 ms             | 0              | 0ms              |
  | 8g   |  39              | 820 ms             | 0              | 0ms              |
 
 
 >总结：
 >
 >QPS没有因为内存的变化获得太大的变化，内存超过8g之后延迟增加了
 >youngGC次数随着内存的增大次数减少，总耗时增加，平均耗时增加，特别是>4g之后，youngGC总耗时增加得很多

G1GC
==== 
命令：
 ```
java -Xms512m -Xms512m -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms1g -Xms1g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms2g -Xms2g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms4g -Xms4g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms8g -Xms8g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms8g -Xms8g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
```
压测结果：

 | 内存 |         Requests/sec   |       Avg Latency|        Max Latency |   Avg Req/Sec   | 
 | :----:|:----: |:----: |:----: |:----: |
 | 512m | 18342.93            | 2.45ms            | 131.54ms           | 9.21k           |
 | 1g   | 18582.21           |  2.51ms            | 156.29ms          | 9.38k              |
 | 2g   | 19517.12           | 2.25ms            | 137.47ms         | 9.86k                |
 | 4g   | 19734.25           | 2.19ms            | 143.94ms          | 9.97k            |
 | 8g   | 16820.18           | 2.65ms         | 139.42ms          | 8.49k            |
  | 8g(无MaxGCPauseMillis)   |  19616.89           | 2.13ms         | 124.84ms          | 9.89k            |

 
  >GC结果：
  >	
  | 内存 | GC平均耗时 | GC最大耗时 | GC次数 | GC总耗时 |
  | ---- | --------------- | ----------------- | -------------- | ---------------- |
  | 512m |  15.9 ms              | 20.0 ms             | 49            | 780 ms             |
  | 1g   |  21.2 ms              | 40.0 ms           | 26          | 550 ms           |
  | 2g   |  19.3 ms              |30.0 ms             | 15              | 290 ms              |
  | 4g   |  25.5 ms              | 30.0 ms             | 11              | 280 ms              |
  | 8g   |  30.0 ms              | 40.0 ms             | 11              | 330 ms              |
 | 8g(无MaxGCPauseMillis)   |  26.7 ms              | 30.0 ms             | 6              | 	160 ms              |
 >总结：
 >
 >QPS没有因为内存的变化获得太大的变化，到了8g之后QPS变小了，尝试取消-XX:MaxGCPauseMillis=50的参数之后QPS又恢复正常了
 >GC的次数随着内存的增加变少，总耗时变短，平均耗时增加，
 >在8g的时候取消掉-XX:MaxGCPauseMillis=50的参数之后之后GC次数、GC总耗时减少，应该是算法在预估耗时的时候有点高估了，因为默认是200ms，但是实际跑下来，最大耗时还是30ms,设置为50m后GC次数增加了反倒是影响了性能



