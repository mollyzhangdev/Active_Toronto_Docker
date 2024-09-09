package com.example.active;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "ActiveTO API", version = "1.0", description = "A open source API that allows users to browse and search drop-in activities at City of Toronto Recreation Facilities.\r\n" +
		"API path: https://www.mollyzhang/apps/activeto/api \n" +
		"** Available city: toronto",
		contact = @Contact(name = "Dongyue Zhang", url = "https://github.com/dongyue-zhang", email = "zhangdongyue22@gmail.com")))
public class ActiveApplication {
	public static void main(String[] args) {
		Locale.setDefault(Locale.CANADA);
		SpringApplication.run(ActiveApplication.class, args);
	}
}
