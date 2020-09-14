import React from "react";

const Login = () => {

    function handleSubmit(event) {
        event.preventDefault();
        //Send request to backend
    }

    return (
        <>
            <form action="submit" onSubmit={handleSubmit}>
                <input type="text" placeholder="Email" required/>
                <input type="text" placeholder="Password"/>
            </form>
            <button className="login-button">
                <a href="#">Login</a>
            </button>
            <p>First time? Create password <a href="#">here</a></p>
        </>
    )

}

export default Login;