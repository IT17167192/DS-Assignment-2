package com.project.AlarmMonitoringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication
public class AlarmMonitoringSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlarmMonitoringSystemApplication.class, args);
	}
}
