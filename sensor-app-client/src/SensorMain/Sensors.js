import React, {Component} from "react";
import Sensor from './Sensor';
import { MDBContainer, MDBRow, MDBCol } from "mdbreact";

class Sensors extends Component {
    constructor(props) {
        super(props);
        //initial state
        this.state = {
            isLoading: true,
            sensors: []
        }
    }

    //get all sensors
    getSensorState() {
        fetch('http://localhost:8080/api/sensors')
            .then(response => {
                //convert response to json format
                return response.json();
            })
            .then(result => {
                //assign state
                //once retrieve all the data loading should disappear
                //response json assign to sensors
                this.setState({isLoading: false, sensors: result});
            })
            .catch(error => {
                //if there is any error
                console.log(error)
            });
    }

    //update sensor function
    //PUT request
    //expecting the sensor id and updated sensor state
    updateSensorState(id, updatedState){
        //calling route ape/sensor/id
        fetch(`http://localhost:8080/api/sensor/${id}`, {
            method: 'PUT',
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(updatedState)
        });
    }

    //to perform updation with random dummy values
    preformDummyUpdate(){
        this.getSensorState();
        this.state.sensors.map(sensor => {
            //looping each sensor
            let updation = sensor;
            //to check status of each sensor has changed while we looping through
            //this might happen if desktop client perform update while looping through each object
            return fetch(`http://localhost:8080/api/sensor/${sensor.id}`)
                .then(response => {
                    return response.json();
                })
                .then(result => {
                    //only perform updation if the current status is active
                    //1 - active, 0 - inactive
                    if(result.status === 1){
                        console.log("status active");
                        //change status randomly
                        //using Math.random
                        //range 0 - 10
                        updation.co2Level = Math.ceil(Math.random() * Math.floor(10));
                        updation.smokeLevel = Math.ceil(Math.random() * Math.floor(10));
                        updation.status = 1;
                        //calling update method
                        this.updateSensorState(sensor.id, updation);
                    }
                })
                .catch(error => {
                    //if there are errors
                    console.log(error)
                });
        });
    }

    componentDidMount() {
        //once the component mount
        //periodically perform dummy update
        //interval has set to 10 seconds
        this.interval = setInterval(() => {
            this.preformDummyUpdate();
        }, 10000);
    }

    render() {
        const {isLoading, sensors} = this.state;
        if (isLoading)
            return <div>Loading ...</div>
        else {
            return (
                <MDBContainer>
                    <h2>Sensors</h2>
                    <MDBRow>
                        <MDBCol sm="12" md="12" lg="6" xl="6">
                            {
                                sensors.map((sensor, i) => {
                                    if(i%2 === 0){
                                        return (
                                            <Sensor id={sensor.id} status={sensor.status} co2Level={sensor.co2Level}
                                                    floorNo={sensor.floorNo} roomNo={sensor.roomNo} smokeLevel={sensor.smokeLevel} key={sensor.id}/>
                                        );
                                    }
                                })
                            }
                        </MDBCol>
                        <MDBCol sm="12" md="12" lg="6" xl="6">
                            {
                                sensors.map((sensor, i) => {
                                    if(i%2 === 1){
                                        return (
                                            <Sensor id={sensor.id} status={sensor.status} co2Level={sensor.co2Level}
                                                    floorNo={sensor.floorNo} roomNo={sensor.roomNo} smokeLevel={sensor.smokeLevel} key={sensor.id}/>

                                        );
                                    }
                                })
                            }
                        </MDBCol>
                    </MDBRow>
                </MDBContainer>
            );
        }
    }
}

export default Sensors;
