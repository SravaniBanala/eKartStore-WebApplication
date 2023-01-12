package edu.northeastern.csye6220.eKartFinalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.northeastern.csye6220.eKartFinalProject.EKartFinalProjectApplication;


@SpringBootApplication
@ComponentScan({"edu.northeastern.csye6220.eKartFinalProject.controller","edu.northeastern.csye6220.eKartFinalProject.pojo",
	"edu.northeastern.csye6220.eKartFinalProject.validator","edu.northeastern.csye6220.eKartFinalProject.exception",
	"edu.northeastern.csye6220.eKartFinalProject.dao"})
public class EKartFinalProjectApplication extends SpringBootServletInitializer implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(EKartFinalProjectApplication.class, args);
	}

}
