package com.project.AlarmMonitoringSystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "sensor")
public class Sensor {
	//auto incrementing the id
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int floorNo;
	private int roomNo;
	private int smokeLevel;
	private int co2Level;
	private int status;
	//a User can add many sensors
	//But sensor is added by one user
	@ManyToOne
	User addedBy;
	
	//a user may responsible for many sensors
	//But each sensor must have one single responsible User
	@ManyToOne
	User responsiblePerson;
}
