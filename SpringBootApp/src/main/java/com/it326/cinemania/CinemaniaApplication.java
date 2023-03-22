package com.it326.cinemania;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Currently, the code found in this springboot application takes a lot of inspiration from this tutorial: https://spring.io/guides/tutorials/rest/ 
   Nothing shown currently has to be permanent, I just wanted to build a basic example to get started with spring-boot. 
*/

@SpringBootApplication
public class CinemaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaniaApplication.class, args);
	}
}
