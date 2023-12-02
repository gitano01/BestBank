package com.bcn;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BestBankApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BestBankApplication.class); 
		Map<String,Object> map = new HashMap<>();
        map.put("server.port", "8080");
        //map.put("server.adress", "0.0.0.0");       
        app.setDefaultProperties(map);
        app.run(args);
	}
}



        
        