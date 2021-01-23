

#SerialGC
堆内存大小越大，GC平均耗时越大,GC次数越少

对于单纯计算的项目来说，使用SerialGC的话，堆内存的设置十分重要，对性能的影响极大，堆内存太小OOM,太大的话GC时间太长

对于和用户交互的项目来说，在gateway-server-0.0.1-SNAPSHOT.jar的测试中，在1g以上的堆内存的对QPS的影响、平均延迟并不大变化也不大，
但是最大延迟却越来越大，这是因为堆内存变大了之后，GC次数减少，单次GC的耗时增加导致部分请求的延迟很高

#ParallelGC
ParallelGC和SerialGC类似，多线程的SerialGC,单次GC的耗时更少，所以可以和SerialGC来进行比较，从而理解ParallelGC

在GCLogAnalysis的测试中 内存<1g 时，ParallelGC的GC次数更多，所以性能不如SerialGC，>1g时性能优于SerialGC
在gateway-server-0.0.1-SNAPSHOT.jar的测试中，各个内存下ParallelGC的表现都优于SerialGC


#调优总结

1.Xmx和Xms的设置要在不影响吞吐的情况下设置得尽量小，设置大了GC时间长延迟会增加，对吞吐也有影响
2.ParallelGC对比CMSGC对吞吐的影响较小，因为ParallelGC不会在业务进行的时候占用CPU的资源，如果考虑延迟就应该使用CMSGC
3.初始的MetaSpace空间大小一般较小，JVM启动的时候MetaSpace太小，将会执行多次FullGC,为了防止这种情况就需要设置初始MetaSpace的大小和最大大小
