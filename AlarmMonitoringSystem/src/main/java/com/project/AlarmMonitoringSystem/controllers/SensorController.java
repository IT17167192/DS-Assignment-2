package com.project.AlarmMonitoringSystem.controllers;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.AlarmMonitoringSystem.models.Sensor;
import com.project.AlarmMonitoringSystem.repositories.SensorRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SensorController {
	private SensorRepository sensorRepository;

	public SensorController(SensorRepository sensorRepository) {
		super();
		this.sensorRepository = sensorRepository;
	}
	
	//method returns all the sensors
	@GetMapping("/sensors")
	@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
	Collection<Sensor> getSensors(){
		return sensorRepository.findAll();
	}
	
	//method finds and return a sensor by unique id
	@GetMapping("/sensor/{id}")
	@CrossOrigin(origins = {"http://localhost:3001"})
	ResponseEntity<?> getSensor(@PathVariable Long id){
		Optional<Sensor> sensor = sensorRepository.findById(id);
		return sensor.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	//method create a sensor
	@PostMapping("/sensor")
	ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) throws URISyntaxException{
		Sensor result = sensorRepository.save(sensor);
		return ResponseEntity.created(new URI("/api/sensor" + result.getId())).body(result);
	}
	
	//method find and update sensor by id
	@PutMapping("/sensor/{id}")
	@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
	ResponseEntity<Sensor> updateSensor(@Valid @RequestBody Sensor sensor){
		Sensor result = sensorRepository.save(sensor);
		return ResponseEntity.ok().body(result);
	}
	
	//method find and delete sensor by id
	@DeleteMapping("/sensor/{id}")
	ResponseEntity<?> deleteSensor(@PathVariable Long id){
		sensorRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/sensor/sendAlert")
	void sendAlert(@Valid @RequestBody Sensor sensor) throws URISyntaxException{
		System.out.println("=============SENDING SMS=============");
		System.out.println("ALERT!\nSensor : " + sensor.getId() 
			+ "\nFloor No : "+ sensor.getFloorNo()
			+ "\nRoom No " + sensor.getRoomNo() 
			+ "\nMessage to : " + sensor.getResponsiblePerson().getMobile());
		System.out.println("=============SENDING EMAIL=============");
		System.out.println("ALERT!\nSensor : " + sensor.getId() 
			+ "\nFloor No : "+ sensor.getFloorNo()
			+ "\nRoom No " + sensor.getRoomNo() 
			+ "\nMessage to : " + sensor.getResponsiblePerson().getEmail());
	}
}
