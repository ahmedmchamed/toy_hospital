import React from 'react';
import ToyForm from './containers/form';
import { BrowserRouter as Router } from 'react-router-dom';
import './App.css';

function App() {


  return (
    <Router>
      <ToyForm />
    </Router>
    
  );
}

export default App;
