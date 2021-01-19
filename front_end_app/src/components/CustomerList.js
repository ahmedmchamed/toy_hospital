import React, {useState} from "react"

const CustomerList = ({customers}) => {
    console.log(customers)

    const customerNames = customers.map(customer => {
        return <li><span>{customer.customerName}</span>   <span>{customer.customerEmail}</span>   <span>{customer.customerAddress}</span></li>
    })

    return (
        // <h1>Customer List TBC...</h1>
        <>
        <span>{customerNames}</span>
        </>
    )
}

export default CustomerList;