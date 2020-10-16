import React, { useEffect, useState } from "react"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import AuthService from "../services/auth.service"
import userService from "../services/user.service"
import NavBar from "./NavBar"
import Login from "./Login"

const Home = (props) => {

    const [content, setContent] = useState(""); //just for testing! Should probs store toys and/or customers
    
    //grabbing stuff from secure API with accesstoken!
    useEffect(() => {
        userService.getUserBoard()
        .then(response => {
            setContent(response.data)
        }, error => {
            console.error(error)
        })
    })

    return (
        <Router>
            <>
                <NavBar />
                <h1>You've successfully logged in!</h1>
                <p>{content}</p>
                <Switch>
                    <Route path="/login" component={Login}/>
                </Switch>
                
            </>
        </Router>
    )
}   

export default Home;