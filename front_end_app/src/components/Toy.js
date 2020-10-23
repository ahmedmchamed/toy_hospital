import React from "react"

const Toy = ({key, name, type, age, repairDescription, repairs, customer, staff}) => {

    return (
    <li>
        <span>{name}</span>
        <span>{customer.name}</span>
        <span>{customer.phoneNumber}</span>
        <span>{customer.email}</span>
        <span>{staff}</span>
    </li>
    )

}

export default Toy;