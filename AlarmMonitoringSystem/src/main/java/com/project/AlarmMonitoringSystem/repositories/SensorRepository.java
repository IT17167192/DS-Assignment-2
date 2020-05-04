package com.project.AlarmMonitoringSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AlarmMonitoringSystem.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
	
}
