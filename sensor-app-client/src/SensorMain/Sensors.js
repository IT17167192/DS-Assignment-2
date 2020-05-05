import React, {Component} from "react";
import Sensor from './Sensor';
import { MDBContainer, MDBRow, MDBCol } from "mdbreact";

class Sensors extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            sensors: []
        }
    }

    getSensorState() {
        fetch('http://localhost:8080/api/sensors')
            .then(response => {
                return response.json();
            })
            .then(result => {
                this.setState({isLoading: false, sensors: result});
            })
            .catch(error => {
                console.log(error)
            });
    }

    updateSensorState(id, updatedState){
        fetch(`http://localhost:8080/api/sensor/${id}`, {
            method: 'PUT',
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(updatedState)
        });
    }

    preformDummyUpdate(){
        this.getSensorState();
        this.state.sensors.map(sensor => {
            let updation = sensor;
            return fetch(`http://localhost:8080/api/sensor/${sensor.id}`)
                .then(response => {
                    return response.json();
                })
                .then(result => {
                    if(result.status === 1){
                        console.log("status active");
                        updation.co2Level = Math.ceil(Math.random() * Math.floor(10));
                        updation.smokeLevel = Math.ceil(Math.random() * Math.floor(10));
                        updation.status = 1;
                        this.updateSensorState(sensor.id, updation);
                    }
                })
                .catch(error => {
                    console.log(error)
                });
        });
    }

    componentDidMount() {
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
