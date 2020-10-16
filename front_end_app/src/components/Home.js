import React, { useEffect, useState } from "react";
import AuthService from "../services/auth.service"
import userService from "../services/user.service";
import Login from "./Login";

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

    const handleLogout = () => {
        AuthService.logout()
        props.history.push("/login")
        window.location.reload()
    }

    return (
        <>
            <h1>You've successfully logged in!</h1>
            <p>{content}</p>
            <button onClick={handleLogout}>Logout</button>
        </>
    )
}   

export default Home;