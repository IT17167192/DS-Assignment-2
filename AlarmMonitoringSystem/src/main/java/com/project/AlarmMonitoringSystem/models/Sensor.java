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
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int floorNo;
	private int roomNo;
	private int smokeLevel;
	private int co2Level;
	private int status;
	
	@ManyToOne
	User addedBy;
	
	@ManyToOne
	User responsiblePerson;
}
