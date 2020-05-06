import React, {Component} from "react";
import Sensor from './Sensor';
import { MDBContainer, MDBRow, MDBCol } from "mdbreact";

class SensorAppMain extends Component {
    constructor(props) {
        super(props);
        //initial state
        this.state = {
            isLoading: true,
            sensors: []
        }
    }

    getSensorState() {
        //get all sensors
        fetch('http://localhost:8080/api/sensors')
            .then(response => {
                return response.json();
            })
            .then(result => {
                //set state and loading to false once we receive the all sensors
                this.setState({isLoading: false, sensors: result});
            })
            .catch(error => {
                //if there is an error
                //display the error
                console.log(error)
            });
    }

    componentDidMount() {
        //after mount component into the ui
        //get all sensors
        //and assign to the state
        this.getSensorState();
        this.interval = setInterval(() => {
            //periodically retrieve sensor state
            this.getSensorState();
        }, 40000);
    }

    //rendering function call child Sensor.js
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

export default SensorAppMain;
