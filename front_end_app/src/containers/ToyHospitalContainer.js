import React, { useState, useEffect } from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Login from "../components/Login"
import Home from '../components/Home'
import AuthenticatedRoute from "../routes/AuthenticatedRoute"
import UnauthenticatedRoute from "../routes/UnauthenticatedRoute"
import AuthService from "../services/auth.service"


const ToyHospital = () => {

    const [isAuthenticated, setIsAuthenticated] = useState(false)

    useEffect(() => {
        onLoad()
    }, [])

    const onLoad = () => {
        if (AuthService.getCurrentUser().accessToken.length !== 0) {
            setIsAuthenticated(true)
        } else {
            console.log("Unauthorised!");
        }
    }

    return (
        <Router>
            <>
                <Switch>
                    <UnauthenticatedRoute
                        path="/login"
                        component={Login}
                        appProps={{ isAuthenticated }} 
                    />
                    <AuthenticatedRoute
                        path="/home"
                        component={Home}
                        appProps={{ isAuthenticated }}
                    />
                    <Route path="/" component={Login} />
                </Switch>
            </>
        </Router>
    )

}

export default ToyHospital;