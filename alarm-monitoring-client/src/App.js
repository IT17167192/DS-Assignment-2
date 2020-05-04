import React from 'react';
import './App.css';
import Navbar from './Nav/Navbar'
import SensorAppMain from "./main/SensorAppMain";
function App() {
  return (
    <div className="App">
        <br/>
        <Navbar/>
        <br/>
        <SensorAppMain/>
    </div>
  );
}

export default App;
