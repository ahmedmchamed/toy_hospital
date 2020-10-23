import React, { useEffect, useState } from "react"
import userService from "../services/user.service"
import AuthService from "../services/auth.service"
import Toy from "./Toy"


const Home = ({toys}) => {

    // const [toys, setToys] = useState([])

    // useEffect(() => {
    //     userService.getToys()
    //     .then(response => {
    //         if (response === "Unauthorised") {
    //             AuthService.logout()
    //             window.location.href = "/login"
    //         }
    //         setToys(response.data)
    //     }, error => {
    //         console.error(error)
    //     })
    // }, [])

    return (
        <>
            <h1>Toys</h1>
            <ul>
            {toys.map(toy => (
                <Toy 
                    key={toy.id}
                    name={toy.name}
                    type={toy.type}
                    age={toy.age}
                    repairDescription={toy.repairFromCustomer}
                    repairs={toy.repair}
                    customer={toy.customer}
                    staff={toy.staff}
                />
            ))}
            </ul>
        </>
    )
}   

export default Home;