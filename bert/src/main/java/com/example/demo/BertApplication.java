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

@SpringBootApplication
@RestController
public class BertApplication {

	public static void main(String[] args) {
		SpringApplication.run(BertApplication.class, args);
	}
	private static final Logger LOG = Logger.getLogger(BertApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/shopping")
	public String call_UNIQLO_Home(){
		LOG.log(Level.INFO, "calling trace bert shopping ...");
		return restTemplate.getForObject("http://localhost:8989/UNIQLO_home", String.class);
	}
	@RequestMapping("/bert_home")
	public String info(){
		LOG.log(Level.INFO, "bert response ok");
		return "i'm bert";

	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}
