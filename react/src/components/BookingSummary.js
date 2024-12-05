import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

export const BookingSummary = () => {
    const [bookingDetails, setBookingDetails] = useState(null);
    const [userDetails, setUserDetails] = useState(null);
    const [traveler, setTraveler] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();
    const purchaseDate = new Date();
    const formattedDate = purchaseDate.toISOString().split('T').join(' ').split('.')[0];

    useEffect(() => {
        const storedBookingDetails = JSON.parse(localStorage.getItem('bookingDetails'));
        const storedUserDetails = JSON.parse(localStorage.getItem('user'));
        const storedTraveler = JSON.parse(localStorage.getItem('storingTraveler'));

        if (!storedBookingDetails || !storedUserDetails || !storedTraveler) {
            setErrorMessage('Required data not found. Please ensure all details are filled.');
            return;
        }

        setBookingDetails(storedBookingDetails);
        setUserDetails(storedUserDetails);
        setTraveler(storedTraveler);
    }, [navigate]);

    if (!bookingDetails) {
        return <div>Loading...</div>;
    }

    const { flightDetails } = bookingDetails;
    const { userObject } = userDetails;
    const numberOfTravelers = parseInt(traveler.traveler, 10);
    const boardingGroup = 'A';

    console.log(userDetails.user.id)

    const handleConfirm = async (e) => {

        try {
            const response = await fetch(`http://localhost:8080/book?userId=${userDetails.user.id}&flightId=${flightDetails.flightId}&numberOfTravelers=${numberOfTravelers}&boardingGroup=${boardingGroup}`,
                {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ flightDetails, userObject }), //////
                })

            const result = await response.json();

            if (response.ok) {
                localStorage.removeItem('bookingDetails');
                localStorage.removeItem('storingTraveler');
                navigate('/confirmation')
            }
            else {
                console.log(result);
                setErrorMessage(result.message || 'Something goes wrong.');
            }
        }
        catch (error) {
            console.error("Network error:", error); // Log the error
            setErrorMessage("Unable to connect to the server. Please try again later.");
        }

    }

    return (
        <div>
            <h2>Booking Summary</h2>
            <p>
                <strong>Departure: </strong>
                {flightDetails.departureCity} at {new Date(flightDetails.departureTime).toLocaleString()}
            </p>
            <p>
                <strong>Arrival: </strong>
                {flightDetails.arrivalCity} at {new Date(flightDetails.arrivalTime).toLocaleString()}
            </p>
            <p>
                <strong>Price: </strong>
                ${flightDetails.price}
            </p>
            <p>
                <strong>Number of travelers: </strong>{numberOfTravelers}

            </p>
            <p>
                <strong>Boarding Group: </strong>Available Upon Checking

            </p>
            <p>
                <strong>Purchase Date: </strong>{formattedDate}

            </p>

            <button onClick={handleConfirm}>Confirm</button>
        </div>
    )
}