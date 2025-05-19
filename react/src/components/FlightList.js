import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const FlightList = ({ flights, traveler, error }) => {
    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState("");
    const [popupVisible, setPopupVisible] = useState(false);

    const handleAddToCart = async (flight) => {
        const user = JSON.parse(localStorage.getItem('user'));

        if (!user) {
            alert('You must be logged in to proceed with booking.');
            
            localStorage.setItem('pendingSearch', JSON.stringify({
                flights,
                traveler,
            }));
        
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

        console.log('user id is', user.user.id, 'flight id ', flight.flightId, 'num traveler ', traveler)

        try {
            const response = await fetch(`http://localhost:8080/cart?userId=${user.user.id}&flightId=${flight.flightId}&numberOfTravelers=${traveler}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
            });

            if (response.ok) {
                setPopupVisible(true);
                setTimeout(() => setPopupVisible(false), 3000);
                console.log("Added to Cart sucessfully")
            } else {
                const errorData = await response.json();
                setErrorMessage(errorData.message || "Failed to add to cart.");
            }
        } catch (error) {
            console.error("Network error:", error);
            setErrorMessage("Unable to connect to the server. Please try again later.");
        }

    }



    return (
        <div className="ResultsWrapper">
            {popupVisible && (
                <div className="Popup">
                    <p>Added to cart successfully!</p>
                </div>
            )}
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
                                    <button onClick={() => handleAddToCart(flight)}>Add to Cart</button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    )
}