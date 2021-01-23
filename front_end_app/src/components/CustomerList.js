import React, {useState} from "react"
import { BrowserRouter as Router, Route, useHistory, Link} from 'react-router-dom'
import CustomerDetail from './CustomerDetail.js'
import './CustomerDetail.css'

const CustomerList = ({customers}) => {
    console.log(customers)

    const history = useHistory();

    const customerLinks = customers.map(customer => {
        return (
            <Link to={`/${customer.customerName}/${customer.id}`}>
                {customer.customerName}
                {customer.customerEmail}
                {customer.customerAddress}
            </Link>
        )   
    })

    const customerRoutes = customers.map(customer => {
        return (
            <Route 
                exact path={`/${customer.customerName}/${customer.id}`}
                render={() => <CustomerDetail />}
            />
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