package com.nl.trace.dataseeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import({TraceCardsDataSourceConfig.class, TraceBankDataSourceConfig.class})
public class TraceDataSeederApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraceDataSeederApplication.class, args);
	}

}
