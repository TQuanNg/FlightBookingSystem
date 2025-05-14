import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';

export const BookingCart = () => {
    const [cart, setCart] = useState([]);
    const [subTotal, setSubTotal] = useState(0);
    const [userDetails, setUserDetails] = useState(null);
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const storedUserDetails = JSON.parse(localStorage.getItem('user'));
        setUserDetails(storedUserDetails);
    }, []);


    useEffect(() => {
        if (!userDetails) return;

        const fetchCart = async () => {
            try {
                const response = await fetch(`http://localhost:8080/cart?userId=${userDetails.user.id}`, {
                    method: 'GET',
                    headers: { 'Content-Type': 'application/json' },
                });

                if (response.ok) {
                    const data = await response.json();
                    console.log(data);
                    setCart(data);
                } else {
                    const errorData = await response.json();
                    setErrorMessage(errorData.message || "Failed to fetch purchase history.");
                }
            } catch (error) {
                console.error("Network error:", error);
                setErrorMessage("Unable to connect to the server. Please try again later.");
            }
        };

        fetchCart();
    }, [userDetails]);

    const handleSubmit = async (cartItemId) => {
        console.log('user detail ', userDetails.user.id, ' cart ', cartItemId)
        try {
            const response = await fetch(`http://localhost:8080/cart?userId=${userDetails.user.id}&cartItemId=${cartItemId}`, {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' },
            });

            if (response.ok) {
                setCart(prevCart => prevCart.filter(item => item.cartId !== cartItemId));
            } else {
                const errorData = await response.json();
                setErrorMessage(errorData.message || "Failed to remove item.");
            }
        } catch (error) {
            console.error("Network error:", error);
            setErrorMessage("Unable to connect to the server. Please try again later.");
        }
    }

    // this funtion should pass value to BookingSummary page
    const handleCheckout = (cartItemId) => {
        const selectedItem = cart.find((item) => item.cartId === cartItemId);
        if (selectedItem) {
            navigate("/booking-summary", { state: { selectedItem } });
        }
    }

    return (
        <div className="booking-cart">
            <h2>Booking Cart</h2>
            {errorMessage && (
                <div style={{ color: 'red', textAlign: 'center', marginBottom: '20px' }}>
                    {errorMessage}
                </div>
            )}
            {cart.length > 0 ? (
                <div className="cart-container" style={{ borderCollapse: 'collapse', width: '80%', margin: '0 auto' }}>
                    <div className="cart-header">
                        <p>Cart ID</p>
                        <p>Flight ID</p>
                        <p>Departure</p>
                        <p>Arrival</p>
                        <p>Price</p>
                        <p>Travelers</p>
                        <p></p>
                    </div>
                    <div>
                        {cart.map((item, index) => (
                            <div className="cart-item">
                                <p>{item.cartId}</p>
                                <p>{item.flightId}</p>
                                <p>{item.departurePlace}</p>
                                <p>{item.arrivalPlace}</p>
                                <p>${item.price.toFixed(2)}</p>
                                <p>{item.numberOfTravelers}</p>
                                <div className="cart-button-group">
                                    <button
                                        className="remove-btn"
                                        onClick={() => handleSubmit(item.cartId)}
                                    >
                                        Remove
                                    </button>
                                    <button
                                        className="checkout-btn"
                                        onClick={() => handleCheckout(item.cartId)}
                                    >
                                        Checkout
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            ) : (
                !errorMessage && (
                    <p style={{ textAlign: 'center' }}>Cart is empty.</p>
                )
            )}
            <p2>Sub Total: $</p2>
        </div>
    )
}