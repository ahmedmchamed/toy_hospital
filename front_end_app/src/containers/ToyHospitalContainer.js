import React, { useState, useEffect } from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Login from "../components/Login"
import LoggedInRouter from '../routes/LoggedInRouter'
import AuthenticatedRoute from "../routes/AuthenticatedRoute"
import UnauthenticatedRoute from "../routes/UnauthenticatedRoute"
import AuthService from "../services/auth.service"


const ToyHospital = () => {

    const [isAuthenticated, setIsAuthenticated] = useState(false)

    useEffect(() => {
        onLoad()
    }, [])

    async function onLoad() {
        try {
            await AuthService.getCurrentUser()
            if (AuthService.getCurrentUser().accessToken.length !== 0) {
                setIsAuthenticated(true)
            }
        } catch {
            console.log("Unauthorised");
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
                        component={LoggedInRouter}
                        appProps={{ isAuthenticated }}
                    />
                    <Route path="/" component={Login} />
                </Switch>
            </>
        </Router>
    )

}

export default ToyHospital;