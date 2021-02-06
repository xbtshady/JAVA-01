import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wait()
 */
public class Thread_9 {
    static Integer a = null;

    static class Until{
        static volatile Boolean status = false;
        synchronized void wantGetA() throws Exception{
            while (status == false){
                wait();
            }
        }
        synchronized void alreadySetA(){
            status = true;
            notifyAll();
        }
    }

    public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        Until until = new Until();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        new Thread(()->{
            a = sum();
            until.alreadySetA();
        }).start();

        until.wantGetA();
        int result = a; //这是得到的返回值
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
