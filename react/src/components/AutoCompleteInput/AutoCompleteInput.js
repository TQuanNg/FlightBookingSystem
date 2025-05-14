import React, { useState } from 'react';
import airports from '../../airports.json';
import './style.css';

export const AutoCompleteInput = ({ value, onChange, placeholder }) => {
    const [suggestions, setSuggestions] = useState([]);

    const handleChange = (e) => {
        const input = e.target.value;
        onChange(input);

        if (input.length > 1) {
            const filtered = airports
                .filter((airport) => {
                    const search = input.toLowerCase();
                    return (
                        airport.city.toLowerCase().includes(search) ||
                        airport.name.toLowerCase().includes(search) ||
                        airport.iata.toLowerCase().includes(search)
                    );
                })
                .slice(0, 6); // limit suggestions

            setSuggestions(filtered);
        } else {
            setSuggestions([]);
        }
    };

    const handleSelect = (value) => {
        onChange(value);
        setSuggestions([]);
    };

    return (
        <div style={{ position: 'relative' }}>
            <input
                type="text"
                className="TripInput"
                value={value}
                onChange={handleChange}
                placeholder={placeholder}
                autoComplete="off"
            />
            {suggestions.length > 0 && (
                <ul className="autocomplete-dropdown">
                    {suggestions.map((sug, index) => (
                        <li
                            key={index}
                            onClick={() => handleSelect(sug.city)}
                            className="autocomplete-item"
                        >
                            {sug.city} - {sug.name} ({sug.iata})
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}