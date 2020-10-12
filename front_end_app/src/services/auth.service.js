import axios from "axios"
import urls from "../secrets/api_urls"

const AUTH_URL = urls.authUrl

const register = (username, email, password) => {
    return axios.post(AUTH_URL + "signup", {
        username,
        email,
        password
    })
};

const login = (username, password) => {
    return axios.post(AUTH_URL + "signin", {
        username,
        password
    })
    .then(response => {
        if (response.data.accessToken) {
            localStorage.setItem("user", JSON.stringify(response.data))
        }

        return response.data
    })
}

const logout = () => {
    localStorage.removeItem("user")
}

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"))
}

export default {
    register,
    login,
    logout,
    getCurrentUser
}