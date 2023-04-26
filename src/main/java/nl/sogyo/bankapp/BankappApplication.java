package nl.sogyo.bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("nl.sogyo.bankapp.*")
//@ComponentScan(basePackages = { "nl.sogyo.bankapp.*" })
//@EntityScan("nl.sogyo.bankapp.*")
public class BankappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappApplication.class, args);
	}

}
