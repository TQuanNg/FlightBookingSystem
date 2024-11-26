import React, {useState} from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

export const OneWaySearch = () => {
    const [destintation, setDestination] = useState('');
    const [depart, setDepart] = useState('');
    const [travaler, setTraveler] = useState('');
    const [endDate, setEndDate] = useState(new Date());
    
    // function to  connect to backend java and query through database

    const handleSubmit = () => {
        
    }

    return(
        <div className="SearchWrapper">
            <form className="TripForm" >
                <input type="text" className="TripInput"
                placeholder='Enter your destination'
                value={destintation}
                onChange={(e) => setDestination(e.target.value)} />

                <input type="text" className="TripInput"
                placeholder='Enter your Departure'
                value={depart}
                onChange={(e) => setDepart(e.target.value)} />

                <input type="text" className="TripInput"
                placeholder='Enter number of traveler'
                value={travaler}
                onChange={(e) => setTraveler(e.target.value)} />

                <DatePicker selected={endDate} onChange={(endDate) => setEndDate(endDate)} />
                
                <button type='submit'>Submit</button>
            </form>
        </div>
    )
}