package com.nl.trace.dataseeder.config.datasource;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.nl.trace.cards.repository",
    entityManagerFactoryRef = "trace_DataSeeder_Parent_Cards_EntityManagerFactory",
    transactionManagerRef = "trace_DataSeeder_Parent_Cards_TransactionManager"
)
public class TraceDataSeeder_Cards_DataSourceConfig {

	/**
	 * tcp server connection created to connect single db from multiple projects. 
	 * @return
	 */
    @Bean(name = "trace_DataSeeder_Parent_Cards_DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.trace-cards-db")//getting configuration properties from application.properties
    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
    	
    	try {
			return DataSourceBuilder.create()
					.url("jdbc:h2:tcp://"+ InetAddress.getLocalHost().getHostAddress() +":9010/D:/data-file-base/h2-db/trace-cards-db;DB_CLOSE_ON_EXIT=FALSE")//JDBC URL
					.username("sa")//DB username
					.password("")//DB password
					.driverClassName("org.h2.Driver")//DB driver
					.build();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
    }

    @Bean(name = "trace_DataSeeder_Parent_Cards_EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("trace_DataSeeder_Parent_Cards_DataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.nl.trace.cards.entity")
                .persistenceUnit("traceCards_dataseeder")
                .build();
    }

    @Bean(name = "trace_DataSeeder_Parent_Cards_TransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("trace_DataSeeder_Parent_Cards_EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
