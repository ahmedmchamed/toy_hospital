import React from "react"
import { NavLink, Redirect } from "react-router-dom"
import AuthService from "../../services/auth.service"

const NavBar = (props) => {

    const handleLogout = () => {
        AuthService.logout()
        window.location.href = "/login"
    }

    return (
        <ul>
            <li>
                <NavLink to="/home">Toys</NavLink>
            </li>
            <li>
                <NavLink to="/customers">Customers</NavLink>
            </li>
            <li>
                <NavLink to="/admin">Admin</NavLink>
            </li>
            <li>
                <button onClick={handleLogout}>Logout</button>
            </li>
        </ul>
    )
}

export default NavBar