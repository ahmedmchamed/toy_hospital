import React, {useState} from "react"
import { Route, Link, Switch} from 'react-router-dom'
import CustomerDetail from './CustomerDetail.js'
import './CustomerDetail.css'

const CustomerList = ({customers}) => {
    console.log(customers)

    const customerLinks = customers.map(customer => {
        return (
            <Link to={`/${customer.customerName}/${customer.id}`}>
                {/* <CustomerDetail 
                    customerName={customer.customerName}
                    customerEmail={customer.customerEmail}
                    customerAddress={customer.customerAddress}
                /> */}
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
                render={() => <CustomerDetail 
                    customerName={customer.customerName}
                    customerEmail={customer.customerEmail}
                    customerAddress={customer.customerAddress} />}
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
        <Switch>
            {customerRoutes}
        </Switch>
        </>
    )
}

export default CustomerList;