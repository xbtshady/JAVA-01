#wrk命令
```
wrk -c40 -d30s http://localhost:8088/api/hello
```

#SerialGC
命令：
 ```
java -Xms512m -Xms512m -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms1g -Xms1g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms2g -Xms2g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms4g -Xms4g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar
java -Xms8g -Xms8g -XX:+PrintGCDetails -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseSerialGC -XX:+PrintGCDateStamps -jar gateway-server-0.0.1-SNAPSHOT.jar

```
>压测结果：

 | 内存 |         Requests/sec   |       Avg Latency|        Max Latency |   Avg Req/Sec   | 
 | :----:|:----: |:----: |:----: |:----: |
 | 512m | 17179.91            | 2.54ms            | 151.38ms           | 8.67k           |
 | 1g   | 19991.48           | 2.15ms            | 117.17ms          | 9.94k             |
 | 2g   | 19701.31           | 2.23ms            | 124.49ms         | 9.94k               |
 | 4g   | 19520.52           | 2.66ms            | 205.16ms          | 9.85k             |
 | 8g   | 19945.56           | 2.48ms         | 199.66ms          | 10.08k             |


总结：从512M换到1gQPS获得提升，1-4g的内存增大对QPS没有明确的影响，最大的延迟却增加了，到了8g内存QPS反而减小了
         
 
#ParallelGC
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

总结：QPS没有因为内存的变化获得太大的变化，内存超过4g之后延迟增加了
#ConcMarkSweepGC
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

压测结果：
总结：QPS没有因为内存的变化获得太大的变化，内存超过8g之后延迟增加了