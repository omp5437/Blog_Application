package com.prakash.blog_app;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@Slf4j
public class BlogAppApplication implements CommandLineRunner {

	static Logger logger= LoggerFactory.getLogger(BlogAppApplication.class);
    @Autowired
	PasswordEncoder passEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
		logger.info("Application started");
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}


