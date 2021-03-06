import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

    static String DRIVER = "com.mysql.jdbc.Driver";
    static String URL = "jdbc:mysql://192.168.0.107:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    static String USERNAME = "root";
    static String PASSWORD = "123456";
    static int MAX = 1000000;
    static int count = 4;
    static String SQL = createSql(MAX /count );

    static  BasicDataSource dataSource = new BasicDataSource();
    static {
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }
    public static void main(String[] args)throws Exception {

        //分批执行sql(循环插入)
        //insert1();

        //使用数据连接池多线程执行sql
        insert2();

    }

    /**
     * 创建可以同时插入count行的sql
     * @param count
     * @return
     */
    static public String createSql(int count){
        StringBuffer sql = new StringBuffer("INSERT INTO `test`.`sc_order4`(`payment`, `payment_type`, `status`, `payment_time`, `consign_time`, `end_time`, `user_id`, `user_address_id`) VALUES ");
        for(int i = 0; i<count;i++){
            sql.append("(1.00, 1, 1, '2021-03-06 22:53:40', '2021-03-06 22:53:43', '2021-03-06 22:53:45', 1, 1),");

        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

    /**
     * 获取数据连接池(普通jdbc)
     * @return
     * @throws Exception
     */
    static Connection getConn1() throws Exception{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
    /**
     * 分批执行sql(循环插入)

     */
    static void insert1() throws Exception{
        Connection conn = getConn1();
        System.out.println("开始执行");
        Long startTime = System.currentTimeMillis();
        while (count != 0){
            count--;
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.execute();
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("本轮耗时 = [" + (endTime - startTime)+ "] ms");
    }

    /**
     * 分批执行sql(多线程同时插入)

     */
    static void insert2() throws Exception{
        System.out.println("开始执行");
        long startTime = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,() -> {
            long endTime = System.currentTimeMillis();
            System.out.println("本轮耗时 = [" + (endTime - startTime)+ "] ms");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        Runnable runnable = () -> {
            try {
                System.out.println("线程开始执行");
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                preparedStatement.execute();
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        };
        while (count != 0){
            count--;
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }
}
