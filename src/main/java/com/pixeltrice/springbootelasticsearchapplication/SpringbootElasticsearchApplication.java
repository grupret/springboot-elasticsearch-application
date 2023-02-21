package com.pixeltrice.springbootelasticsearchapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.pixeltrice")
public class SpringbootElasticsearchApplication {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String forObject = restTemplate.getForObject("http://cdn.jsdelivr.net/npm/vue@2.6.14",
			String.class);
		System.out.println(forObject);
//		SpringApplication.run(SpringbootElasticsearchApplication.class, args);
	}

}
