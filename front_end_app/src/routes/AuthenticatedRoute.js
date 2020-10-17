import React from "react"
import { Redirect, Route } from "react-router-dom"

const AuthenticatedRoute = ({ component: LoggedInRouter, appProps, ...rest}) => {
    return (
        <Route
            {...rest}
            render = {props => 
                appProps.isAuthenticated 
                ? <LoggedInRouter {...props} {...appProps} />
                : <Redirect to={`/login?redirect=${props.location.pathname}${props.location.search}`} />
            }
        />
    )
}

export default AuthenticatedRoute