import React from "react"

const Toy = ({key, toyName, toyType, toyAge, repairDescription, repairs, customer, staff}) => {

    return (
    <>
    <li>
        <span>toy name: {toyName}</span>
        <span>toy age: {toyAge}</span>
        <span>customer: {customer.customerName}</span>
        <span>{customer.customerPhoneNumber}</span>
        <span>{customer.customerEmail}</span>
        <span>{staff}</span>
    </li>
    </>
    )

}

export default Toy;