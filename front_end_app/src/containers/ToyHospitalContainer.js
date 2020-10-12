import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Login from "../components/Login"
import Home from '../components/Home'
import AuthService from "../services/auth.service"


const ToyHospital = () => {

    if (AuthService.getCurrentUser().accessToken.length !== 0) {
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
    } else {
        return (
            <Route path="/" component={Login} />
        )
    }

}

export default ToyHospital;