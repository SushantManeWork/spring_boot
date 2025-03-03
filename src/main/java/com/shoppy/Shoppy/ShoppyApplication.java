package com.shoppy.Shoppy;

import com.shoppy.Shoppy.repository.UserPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppyApplication.class, args);
	}

}
