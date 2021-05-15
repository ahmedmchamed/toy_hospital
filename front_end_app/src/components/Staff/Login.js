import React, { useState } from "react"
import { useForm } from "react-hook-form"

import AuthService from "../../services/auth.service"

const Login = (props) => {

    const { register, handleSubmit, watch, errors } = useForm()

    const [loading, setLoading] = useState(false) //To do: Add loading icon!

    const handleLogin = (data) => {

        setLoading(true)

        AuthService.login(data.username, data.password).then(
            () => {
                props.history.push("/home")
                window.location.reload()
            }, error => {
                alert("Wrong password or username, please try again!")
                setLoading(false);
            },
            setLoading(false)
        )

    }
    
    return (
        <div>
            <form onSubmit={handleSubmit(handleLogin)}>
                <label htmlFor="username">Username</label>
                <input type="text" name="username" placeholder="Username" ref={register({required: true})}/>
                {errors.username && <span>Required field</span>}
                <label htmlFor="password">Password</label>
                <input type="password" name="password" placeholder="Password" ref={register({required: true})}/>
                {errors.password && <span>Required field</span>}
                <input type="submit" value="Login"/>
            </form>
        </div>
    )

}

export default Login;