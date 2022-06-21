package com.nttdata.bc19.msmanagementcreditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsManagementCreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsManagementCreditCardApplication.class, args);
	}

}
