import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用额外的类 ReentrantLock、Condition
 */
public class Thread_10 {


    static class Until{
        static Integer a = null;
        final Lock lock = new ReentrantLock();
        final Condition done = lock.newCondition();

        Integer wantGetA() throws Exception{
            lock.lock();
            try {
                while (a == null){
                    done.await();
                }
            }finally {
                lock.unlock();
            }
            return a;
        }
        void alreadySetA(Integer a1){
            lock.lock();
            try {
                a = a1;
                done.signalAll();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        Until until = new Until();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        new Thread(()->{
            until.alreadySetA(sum());
        }).start();


        int result = until.wantGetA(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }


    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
