package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class UniqloApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniqloApplication.class, args);
	}
	
	private static final Logger LOG = Logger.getLogger(UniqloApplication.class.getName());

	@RequestMapping("/UNIQLO_home")
	public String UNIQLO_home(){
		LOG.log(Level.INFO, "UNIQLO response ok");
		return "welcome, What kind of clothes do you want to buy?";
	}

	@RequestMapping("/place_client_order")
	public String call_bert_home(){
		LOG.log(Level.INFO, "calling trace UNIQLO  place client order...");
		return restTemplate.getForObject("http://localhost:8988/bert_home",String.class);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	

}
