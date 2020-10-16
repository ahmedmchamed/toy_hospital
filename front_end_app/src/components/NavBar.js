import React from "react"
import { Link, Redirect } from "react-router-dom"
import AuthService from "../services/auth.service"

const NavBar = (props) => {

    const handleLogout = () => {
        AuthService.logout()
        window.location.href = "/login"
    }

    return (
        <ul>
            <li>
                <Link to="/customers">Customers</Link>
            </li>
            <li>
                <Link to="/admin">Admin</Link>
            </li>
            <li>
                <button onClick={handleLogout}>Logout</button>
            </li>
        </ul>
    )
}

export default NavBar