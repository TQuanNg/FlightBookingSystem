import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';

export const PurchaseHistory = () => {
    const [userDetails, setUserDetails] = useState(null);
    const [history, setHistory] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();
    const userId = 0; // Replace this with the actual user ID from your context or state.

    useEffect(() => {
        const storedUserDetails = JSON.parse(localStorage.getItem('user'));
        setUserDetails(storedUserDetails);
    }, []);

    useEffect(() => {
        console.log('runnnnn')
        if (!userDetails) return;
        console.log('runnnnsasasan')

        const fetchPurchaseHistory = async () => {
            try {
                const response = await fetch(`http://localhost:8080/history?userId=${userDetails.user.id}`, {
                    method: 'GET',
                    headers: { 'Content-Type': 'application/json' },
                });

                if (response.ok) {
                    const data = await response.json();
                    console.log(data)
                    setHistory(data);
                } else {
                    const errorData = await response.json();
                    setErrorMessage(errorData.message || "Failed to fetch purchase history.");
                }
            } catch (error) {
                console.error("Network error:", error);
                setErrorMessage("Unable to connect to the server. Please try again later.");
            }
        };

        fetchPurchaseHistory();
    }, [userDetails]);

    return(
        <div>
            <h2>Purchase History</h2>
            {errorMessage && (
                <div style={{ color: 'red', textAlign: 'center', marginBottom: '20px' }}>
                {errorMessage}
                </div>
            )}
            {userId === 0 ? (
                <table style={{ borderCollapse: 'collapse', width: '80%', margin: '0 auto' }}>
                    <thead>
                        <tr>
                            <th className='tableHeaderStyle'>Date</th>
                            <th className='tableHeaderStyle'>Depart</th>
                            <th className='tableHeaderStyle'>Origin</th>
                            <th className='tableHeaderStyle'>Destination</th>
                            <th className='tableHeaderStyle'>Arrival</th>
                            <th className='tableHeaderStyle'>Flight Number</th>
                            <th className='tableHeaderStyle'>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {history.map((item, index) => (
                            <tr key={index} style={{ textAlign: 'center', border: '1px solid #ddd' }}>
                                <td>{new Date(item.date).toLocaleString()}</td>
                                <td>{new Date(item.depart).toLocaleString()}</td>
                                <td>{item.origin}</td>
                                <td>{item.destination}</td>
                                <td>{new Date(item.arrival).toLocaleString()}</td>
                                <td>{item.flightNumber}</td>
                                <td>${item.price.toFixed(2)}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                !errorMessage && (
                    <p style={{ textAlign: 'center' }}>No purchase history found.</p>
                )
            )}
        </div>
    )
}
