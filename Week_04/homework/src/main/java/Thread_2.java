import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock、Condition
 */
public class Thread_2 {
    static int a = 0;

    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        new Thread(()->{
            lock.lock();
            a = sum();
            condition.signalAll();
            lock.unlock();
        }).start();

        lock.lock();
        try {
            condition.await();
            int result = a; //这是得到的返回值
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为："+result);
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
            // 然后退出main线程
        }finally {
            lock.unlock();
        }

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