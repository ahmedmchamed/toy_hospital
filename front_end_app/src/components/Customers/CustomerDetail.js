import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './CustomerDetail.css'

const CustomerDetail = (props) => {

    const params = useParams();

    console.log(params)

    return (
        <>
            <h1>{params.customerName}</h1>
        </>
    )

}

export default CustomerDetail;