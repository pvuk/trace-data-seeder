package com.nl.trace.dataseeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//@Configuration
public class JpaAuditingConfig {
//	@Bean
	public AuditorAware auditorProvider() {
		return new SpringSecurityAuditorAware();
	}
}
