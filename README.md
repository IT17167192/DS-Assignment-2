# DS-Assignment-2
========= Sensor application using JAVA RMI, ReactJS and Spring boot=========

===============FIRE ALARM MONITORING SYSTEM GUIDE===============

Step 1 :
	- Components
		- AlarmMonitoringSystem
			- REST API
			- Coded in Java Spring Boot
			- Usage of Spring Tool Suite is Recommended
		
		- DesktopClient
			- Coded in Java Swing
			
		- alarm-monitoring-client
			- Coded in React JS
			- Running on Port 3000	
			
		- sensor-app-client
			- Coded in React JS
			- Running on Port 3001

Step 2 :
	- Add "lombok" Excecutable Jar File to the IDE running the REST
		- Path = "C:\Users\<Username>\.m2\repository\org\projectlombok\lombok\<Version-Directory>"
		- Run "lombok" Excecutable Jar File
		- Add the necessary IDEs
		- Install / Update
		- Quit Installer
		- Restart the selected IDEs
		- Clean / Rebuild all projects
	
Step 3 :
	- Run the Rest API "AlarmMonitoringSystem"
		
Step 4 :
	- Run "alarm-monitoring-client" on Port 3000
	
Step 5 :
	- Run "sensor-app-client" on Port 3001
	
Step 6 :
	- Add Sensors from the "DesktopClient"
		- Run "ServerInit.java" in "com.dc.server"
		- Run "Main.java" in "com.dc.view"
		- Complete necessary fields and Add Fire-Sensors
		- Observe "sensor-app-client"
		- Observe "alarm-monitoring-client"

	- Edit Sensors from the "DesktopClient"
		- Select a Sensor from Sensors table
		- Edit necessary features
		- Observe "sensor-app-client"
		- Observe "alarm-monitoring-client"
