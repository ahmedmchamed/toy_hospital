import React from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Login from "../components/Login";
import Home from '../components/Home';

const ToyHospital = () => {

    render() {
        return (
            <Router>
                <>
                    <Switch>
                    <Route exact path="/" component={Login} />
                    <Route exact path="/home" component={Home} />
                    </Switch>
                </>
            </Router>
        )
    }
}

export default ToyHospital;