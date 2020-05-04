import React from 'react';
import './App.css';
import Navbar from './Nav/Navbar';
import Sensors from './SensorMain/Sensors'

function App() {
  return (
    <div className="App">
      <br/>
      <Navbar/>
      <br/>
      <br/>
      <Sensors/>
    </div>
  );
}

export default App;
