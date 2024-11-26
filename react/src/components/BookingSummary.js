import React, { useState, useEffect } from "react";
import axios from "axios";

const BookingSummary = () => {
    const [summary, setSummary] = useState();

    useEffect(() => {

    });

    return(
        <div>
            <h2>Booking Summary</h2>
            <p>
                <strong>Departure:</strong>
                
            </p>
            <p>
                <strong>Arrival:</strong>
                
            </p>
            <p>
                <strong>Price:</strong>
                
            </p>
            <p>
                <strong>Purchase Date:</strong>
                
            </p>
            <p>
                <strong>Number of travelers:</strong>
                
            </p>
            <p>
                <strong>Boarding Group:</strong>
                
            </p>

        </div>
    )
}