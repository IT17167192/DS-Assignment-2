import React, { Component } from "react";
import {Bar} from "react-chartjs-2";

class Sensor extends Component {
    constructor(props) {
        super(props);
        let co2RGBColor = "rgba(98,  182, 239,0.4)";
        let smokeRGBColor = "rgba(98,  182, 239,0.4)";

        if (props.smokeLevel > 5){
            smokeRGBColor = "rgba(255, 134,159,0.4)";
        }

        if(props.co2Level > 5){
            co2RGBColor = "rgba(255, 134,159,0.4)";
        }

        this.state = {
            id: props.id,
            roomNo: props.roomNo,
            floorNo: props.floorNo,
            status: props.status,
            chartData: {
                labels: ["Smoke Level", "CO2 Level"],
                datasets: [{
                    data: [props.smokeLevel, props.co2Level],
                    backgroundColor: [
                        smokeRGBColor,
                        co2RGBColor,
                    ]
                }]
            }
        }
    }

    //if the state has changed, componentWillReceiveProps will update the UI with new state
    componentWillReceiveProps(nextProps) {
        let co2RGBColor = "rgba(98,  182, 239,0.4)";
        let smokeRGBColor = "rgba(98,  182, 239,0.4)";

        if (nextProps.smokeLevel > 5){
            smokeRGBColor = "rgba(255, 134,159,0.4)";
        }

        if(nextProps.co2Level > 5){
            co2RGBColor = "rgba(255, 134,159,0.4)";
        }

        this.setState({
            id: nextProps.id,
            roomNo: nextProps.roomNo,
            floorNo: nextProps.floorNo,
            status: nextProps.status,
            chartData: {
                labels: ["Smoke Level", "CO2 Level"],
                datasets: [{
                    data: [nextProps.smokeLevel, nextProps.co2Level],
                    backgroundColor: [
                        smokeRGBColor,
                        co2RGBColor,
                    ]
                }]
            }
        });
    }

    render() {
        return (
            <div>
                <h5 className="mt-5" style={{fontWeight: "bold"}}>SENSOR ID : {this.props.id}</h5>
                <span className="mt-1 mr-3" style={{fontWeight: "bold"}}>FLOOR NO : {this.state.floorNo}</span>
                <span className="mt-1 mr-3" style={{fontWeight: "bold"}}>ROOM NO : {this.state.roomNo}</span>
                <span className="mt-1 mb-5" style={{fontWeight: "bold"}}>SENSOR STATUS : <span style={this.props.status === 1 ? {color: 'green', fontWeight: "bold"} : {color: 'red', fontWeight: "bold"}}>{this.props.status === 1 ? 'ACTIVE' : 'INACTIVE'}</span></span>
                <br/>
                <br/>
                <div className="chart">
                    <Bar
                        data={this.state.chartData}
                        options={{
                            legend: {
                                display: false
                            },
                            scales: {
                                xAxes: [
                                    {
                                        barPercentage: 1,
                                        gridLines: {
                                            display: true,
                                            color: "rgba(0, 0, 0, 0.1)"
                                        }
                                    }
                                ],
                                yAxes: [
                                    {
                                        gridLines: {
                                            display: true,
                                            color: "rgba(0, 0, 0, 0.1)"
                                        },
                                        ticks: {
                                            beginAtZero: true
                                        }
                                    }
                                ]
                            }
                        }}
                    />
                </div>
            </div>
        );
    }
}

export default Sensor;
