import axios from "axios"
import authHeader from "./auth-header"
import urls from "../secrets/api_urls"

const API_URL = urls.apiUrl

const getToys = () => {
    return axios.get(API_URL + "toys", { headers: authHeader() })
}

const getCustomers = () => {
    return axios.get(API_URL + "customers", { headers: authHeader() })
}

const getRepairs = () => {
    return axios.get(API_URL + "repairs", { headers: authHeader() })
}

const getStaff = () => {
    return axios.get(API_URL + "staff", { headers: authHeader() })
}

const getPublicContent = () => {
    return axios.get(API_URL + "all")
}

const getUserBoard = () => {
    return axios.get(API_URL + "user", { headers: authHeader() })
}

const getAdminBoard = () => {
    return axios.get(API_URL + "admin", { headers: authHeader() })
}

export default {
    getPublicContent,
    getUserBoard,
    getAdminBoard,
    getToys,
    getCustomers,
    getRepairs,
    getStaff
}