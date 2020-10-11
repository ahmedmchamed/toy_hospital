import React, { Component } from "react";
import { Link } from "react-router-dom";
import Axios from "axios";

class Login extends Component {

    constructor() {
        super();
        this.state = {
           username: "admin",
           password: "admin" 
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        
        const endPoint = "http://localhost:8080/authenticate";

        const username = this.state.username;
        const password = this.state.password;

        const user_object = {
            username: username,
            password: password
        };

        Axios.post(endPoint, user_object)
        .then(res => {
            localStorage.setItem("authorization", res.data.token);
            return this.handleTest();
        });
    };

    handleTest() {
        Axios.get("http://localhost:8080/logged-in")
        .then(res => {
            if (res.data === "Authorized") {
                this.props.history.push("/home");
            } else {
                alert("Authentication failure");
            }
        });
    };

    
    render() {
        return (
        <>
            <form action="submit" onSubmit={this.handleSubmit}>
                <input type="text" placeholder="Email" value="admin" required/>
                <input type="password" placeholder="Password" value="admin"/>
                <button type="submit">Login</button>
            </form>

            <p>First time? Create password <a href="#">here</a></p>
        </>
        )
    }

}

export default Login;