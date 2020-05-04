import React, { Component } from "react";
import { MDBBtn, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText} from 'mdbreact';

class Sensor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            co2Level : props.co2Level,
            smokeLevel: props.smokeLevel
        }
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            co2Level : nextProps.co2Level,
            smokeLevel: nextProps.smokeLevel
        })
    }

    render() {
        return (
            <div className="mt-5">
                <MDBCard>
                    <MDBCardBody>
                        <MDBCardTitle>SENSOR ID : {this.props.id}</MDBCardTitle>
                        <MDBCardText>

                           CO2 LEVEL : {this.state.co2Level} {' || '}
                           SMOKE LEVEL : {this.state.smokeLevel}
                        </MDBCardText>
                    </MDBCardBody>
                </MDBCard>
            </div>
        );
    }
}

export default Sensor;
