package com.nl.trace.dataseeder.config.app.postprocessor;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import com.nl.trace.dataseeder.service.error.TraceBankErrorService;

/**
 * class path should be mention in following path >> /trace-data-seeder/src/main/resources/META-INF/spring.factories
 */
public class CustomEnvProcessor implements EnvironmentPostProcessor {
	
	private final Logger LOG = LoggerFactory.getLogger(CustomEnvProcessor.class);
	
    private Server tcpServer;
    
    /**
     * H2 is light weight Java-based relational database that supports both embedded and server modes.
     * To allow multiple servers or applications to connect to the same H2 database, you must run H2 in server mode.
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("EnvironmentPostProcessor: Initializing Before application.properties");
        /*
    	 * Code Ref:
    	 * Exception SQLException is not compatible with throws clause in ApplicationContextInitializer<ConfigurableApplicationContext>.initialize(ConfigurableApplicationContext)
    	 */
    	try {
            // You can register beans or do early setup here
            
            tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9010").start();
        	
        	/*
        	 * Code Ref: Avoid Hardcoding Port
    			Let H2 pick a free port automatically:
        	 */
//            tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers").start();

            LOG.info("H2 server started and listening on port "+ tcpServer.getPort());
		} catch (SQLException e) {
			String errMessage = e.getMessage();
			LOG.error("H2 Configuration Failed to run on manual port. Message: "+ errMessage);
			if(e.getMessage().contains("Address already in use")) {
				int port = 0;
				TraceBankErrorService.killPortInWindows(port, tcpServer, errMessage);
			}
		}
    }
}
