import React, {useState} from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { FlightList } from './FlightList';

export const RoundTripSearch = () => {
    const [destination, setDestination] = useState('');
    const [depart, setDepart] = useState('');
    const [traveler, setTraveler] = useState('');
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [flights, setFlights] = useState([]);
    const [error, setError] = useState('');
    const [searchError, setSearchError] = useState('');

    
    // function to connect to backend java and query through database
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!destination || !depart || !traveler || !startDate || !endDate) {
            setSearchError('All fields are required.');
            return;
        }

        const startTime = startDate.toISOString().slice(0, 19);
        const endTime = endDate.toISOString().slice(0, 19);

        try {
            const response = await fetch (`http://localhost:8080/flights/search?departureCity=${depart}&arrivalCity=${destination}&startTime=${startTime}&endTime=${endTime}&numTravelers=${traveler}`, {
                method: 'GET',
                header: {
                    'Content-Type': 'application/json',
                },
            });
            
            const result = await response.json();

            if (response.ok) {
                setFlights(result);
            }
            else if (response.status === 404) {
                setError('No flights found for the selected criteria.');
                setFlights([]);
            }
            else {
                setError('An error occurred while fetching flights.');
                setFlights([]);
            }
        }
        catch {
            setError('Unable to connect to the server.');
            setFlights([]);
        }
    }

    return(
        <div>
            <div className="SearchWrapper">
            {searchError && <div className="ErrorSearchWrapper">{searchError}</div>}
                <form className="TripForm" onSubmit={handleSubmit}>
                    <input type="text" className="TripInput"
                    placeholder='Enter your destination'
                    value={destination}
                    onChange={(e) => setDestination(e.target.value)} />

                    <input type="text" className="TripInput"
                    placeholder='Enter your Departure'
                    value={depart}
                    onChange={(e) => setDepart(e.target.value)} />

                    <input type="text" className="TripInput"
                    placeholder='Enter number of traveler'
                    value={traveler}
                    onChange={(e) => setTraveler(e.target.value)} />

                    <DatePicker selected={startDate} onChange={(startDate) => setStartDate(startDate)} />
                    <DatePicker selected={endDate} onChange={(endDate) => setEndDate(endDate)} minDate={startDate} />
                    
                    <button type='submit'>Submit</button>
                </form>
            </div>

            <FlightList flights={flights} traveler={traveler} error={error}/>
        </div>
    )
}