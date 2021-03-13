package com.example.xa;


import com.example.xa.conf.TransactionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TransactionConfiguration.class)
public class XaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XaApplication.class, args);
    }

}
