package com.gm.brasilerao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@EnableFeignClients
public class BrasileraoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BrasileraoApplication.class, args);



	}

}
