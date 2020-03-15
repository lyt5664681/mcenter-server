package com.primeton.wh.mcenter;

import com.primeton.eos.dap.sdk.api.bizflow.EnableSDKBizflows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients(basePackages = {"com.primeton.wh.mcenter.api"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableSDKBizflows
@EnableTransactionManagement
@MapperScan("com.primeton.wh.mcenter.impl.mapper")
public class MsgBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(MsgBootApplication.class, args);
    }

}
