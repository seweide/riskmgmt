package com.haier.hairy.rmp;

import com.haier.hairy.common.apollo.configuration.EnableHryApollo;
import com.haier.hairy.common.springboot.feign.EnableHryFeignClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.retry.annotation.EnableRetry;


@EnableDiscoveryClient
@SpringBootApplication
@EnableHryFeignClient
@EnableHryApollo
@EnableRetry(proxyTargetClass = true)
@MapperScan(basePackages = "com.haier.hairy.rmp.dao")
public class RiskmgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskmgmtApplication.class, args);
    }
}
