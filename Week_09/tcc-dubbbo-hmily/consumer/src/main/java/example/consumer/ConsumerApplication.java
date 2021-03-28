package example.consumer;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * DubboTccOrderApplication.
 *
 * @author xiaoyu
 */

@MapperScan("org.example.common.order.mapper")
@ImportResource({"classpath:spring-dubbo.xml"})
@SpringBootApplication(exclude = {JtaAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class ConsumerApplication {

    /**
     * main.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
