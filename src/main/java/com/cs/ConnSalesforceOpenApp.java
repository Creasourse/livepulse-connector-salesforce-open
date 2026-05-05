package com.cs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Salesforce 连接器应用启动类
 *
 * @author LivePulse
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("com.cs.mapper")
public class ConnSalesforceOpenApp {

    public static void main(String[] args) {
        SpringApplication.run(ConnSalesforceOpenApp.class, args);
    }
}
