package com.unitbv.mi.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients("com.unitbv.mi")
@EnableDiscoveryClient
@EntityScan(basePackages="com.unitbv.mi.userManagement.dto")
public class CustomerTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerTestApplication.class, args);
	}

}

