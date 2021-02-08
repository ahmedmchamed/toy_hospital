import React from 'react';
import './App.css';
import ToyHospitalContainer from "./containers/ToyHospitalContainer";
import {BrowserRouter as Router} from 'react-router-dom';

function App() {
  return (
    <Router>
      <ToyHospitalContainer />
    </Router>
  );
}

export default App;
