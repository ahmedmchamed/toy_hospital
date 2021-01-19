import React, { useEffect, useState } from "react"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import Home from "../components/Home"
import NavBar from "../components/NavBar"
import CustomerList from "../components/CustomerList"
import Admin from "../components/Admin"
import userService from "../services/user.service"
import AuthService from "../services/auth.service"

const LoggedInRouter = () => {
    
    const [toys, setToys] = useState([])
    const [customers, setCustomers] = useState([])
    const [repairs, setRepairs] = useState([])
    const [staff, setStaff] = useState([])

    //Not very DRY... 
    useEffect(() => {
        userService.getToys()
        .then(response => {
            console.log(response)
            console.log(response.data)
            setToys(response.data)
        }, error => {
            console.log(error.data);
            handleUnauthorisedResponse()
            console.error();
        })
        userService.getCustomers()
        .then(response => {
            // console.log(response)
            // console.log(response.data)
            setCustomers(response.data)
            console.log(customers)
        }, error => {
            handleUnauthorisedResponse()
            console.error();
        })
        userService.getRepairs()
        .then(response => {
            setRepairs(response.data)
        }, error => {
            handleUnauthorisedResponse()
            console.error();
        })
        userService.getStaff()
        .then(response => {
            setStaff(response.data)
        }, error => {
            handleUnauthorisedResponse()
            console.error();
        })
    }, [])

    // Might have to redo, this approach might kick us out a lot!
    const handleUnauthorisedResponse = () => {
        AuthService.logout()
        window.location.href = "/login"
    }


    return (
        <Router>
            <>
                <NavBar />
                <Switch>
                    <Route path="/home" render={(props) => (<Home toys={toys}/>)}/>
                    <Route path="/customers" render={(props) => (<CustomerList customers={customers} toys={toys} />)} />
                    <Route path="/admin" render={(props) => (<Admin repairs={repairs} staff={staff}/>)} />
                </Switch>
            </>
        </Router>
    )
}

export default LoggedInRouter;