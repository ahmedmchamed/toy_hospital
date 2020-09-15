import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Login from "../components/Login";
import Home from '../components/Home';
import interceptors from "../Interceptors";

class ToyHospital extends Component {

    constructor(props) {
        super(props);
    }

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