import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const FlightList = ({ flights, traveler, error }) => {
    const navigate = useNavigate();

    const handleSelectFlight = (flight) => {
        // Get user info from localStorage
        const user = JSON.parse(localStorage.getItem('user'));

        if (!user) {
            alert('You must be logged in to proceed with booking.');
            navigate('/login');
            return;
        }

        const bookingDetails = {
            userId: user.id,
            firstName: user.firstName,
            lastName: user.lastName,
            flightDetails: flight,
        };

        const storingTraveler = {traveler}

        localStorage.setItem('bookingDetails', JSON.stringify(bookingDetails));
        localStorage.setItem('storingTraveler', JSON.stringify(storingTraveler));
        navigate('/booking-summary');
    }

    return (
        <div className="ResultsWrapper">
            {error && <p className="Error">{error}</p>}
            {flights.length > 0 && (
                <div className="FlightResults">
                    <h3>Search Results</h3>
                    <ul>
                        {flights.map((flight, index) => (
                            <li key={index}>
                                <div>
                                    <h3>Flight ID: {flight.flightId}</h3>
                                    <p>Departure: {flight.departureCity}, Arrival: {flight.arrivalCity}, Date: {flight.departureTime}, Price: {flight.price}</p>
                                    <button onClick={() => handleSelectFlight(flight)}>Select</button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    )
}