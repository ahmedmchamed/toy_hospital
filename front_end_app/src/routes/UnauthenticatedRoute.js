import React from "react"
import { Redirect, Route } from "react-router-dom"
import Login from "../components/Staff/Login"

const UnauthenticatedRoute = ({component: Login, appProps, ...rest}) => {
    
    return (
        <Route
            {...rest}
            render = {props => 
                !appProps.isAuthenticated 
                ? <Login {...props} {...appProps} />
                : <Redirect to={`/home`} />
            }
        />
    )
}

export default UnauthenticatedRoute
