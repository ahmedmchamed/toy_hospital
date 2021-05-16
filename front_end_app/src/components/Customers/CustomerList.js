import React, { useState } from "react"
import { Link } from 'react-router-dom'
import './CustomerDetail.css'

const CustomerList = ({customers}) => {
    console.log(customers)

    const customerLinks = customers.map(customer => {
        return (
            <Link to={`/customers/${customer.customerName}/${customer.id}`}>
                {customer.customerName}
            </Link>
        )   
    })

    return (
        <>
            <ul>
                <li>
                    {customerLinks}
                </li>
            </ul>
        </>
    )
}

export default CustomerList;