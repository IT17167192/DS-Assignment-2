package com.project.AlarmMonitoringSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AlarmMonitoringSystem.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
