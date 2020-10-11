import React, { useState, useRef } from "react"
import Form from "react-validation/build/form"
import Input from "react-validation/build/input"
import CheckButton from "react-validation/build/button"

import AuthService from "../services/auth.service"

const required = (value) => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        )
    }
}

const Login = (props) => {

    const form = useRef()
    const checkBtn = useRef()

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [loading, setLoading] = useState(false)
    const [message, setMessage] = useState("")

    const onChangeUsername = (event) => {
        const username = event.target.value
        setUsername(username)
    }

    const onChangePassword = (event) => {
        const password = event.target.value
        setPassword(password)
    }

    const handleLogin = (event) => {
        event.preventDefault()

        console.log("Button clicked");

        setMessage("")
        setLoading(true)

        form.current.validateAll()

        if (checkBtn.current.context._errors.length === 0) {
            AuthService.login(username, password).then(
                () => {
                    console.log("Yaaay, logged in!");
                    props.history.push("/home")
                    window.location.reload()
                },
                (error) => {
                    const resMessage = (error.response && error.response.data && error.response.data.message) ||error.message || error.toString()
                    console.log("You failed...");
                    setLoading(false)
                    setMessage(resMessage)
                }
            )
        } else {
            setLoading(false)
        }
    }
    
    return (
        <div>
            <Form onSubmit={handleLogin} ref={form}>
                <label htmlFor="username">Username</label>
                <input type="text" name="username" value={username} onChange={onChangeUsername} validations={[required]}/>
                <label htmlFor="password">Password</label>
                <input type="password" name="password" value={password} onChange={onChangePassword} validations={[required]}/>
                <button disabled={loading}>
                    {loading && (
                        <span></span>
                    )}
                    <span>Login</span>
                </button>
                <CheckButton style={{ display: "none"}} ref={checkBtn}></CheckButton>
            </Form>
        </div>
    )

}

export default Login;