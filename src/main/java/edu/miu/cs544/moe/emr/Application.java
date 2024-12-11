package edu.miu.cs544.moe.emr;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "EMR API",
		description = "RESTful API for managing electronic medical records, enabling efficient storage, retrieval, and updates of patient data.",
		version = "0.0.1",
		contact = @Contact(name = "Aung Thu Moe", email = "amoe@miu.edu")
))
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
