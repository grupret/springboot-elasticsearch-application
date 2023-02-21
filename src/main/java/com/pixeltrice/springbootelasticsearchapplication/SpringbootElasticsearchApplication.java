package com.pixeltrice.springbootelasticsearchapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.pixeltrice")
public class SpringbootElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootElasticsearchApplication.class, args);
	}

}
