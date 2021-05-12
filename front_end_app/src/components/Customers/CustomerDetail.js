import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom'
import './CustomerDetail.css'

const CustomerDetail = ({customerName, customerAddress, customerEmail}) => {

    console.log(customerName)
    return (
        <>
            <h1>{customerName}</h1>
        </>
    )

}

export default CustomerDetail;