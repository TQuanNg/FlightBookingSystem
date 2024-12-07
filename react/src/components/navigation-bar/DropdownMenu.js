import React, { useState } from 'react';
import { Link } from 'react-router-dom';

export const DropdownMenu = ({ handleLogout }) =>  {
    const [dropdownVisible, setDropdownVisible] = useState(false);
    
    const toggleDropdown = () => {
        setDropdownVisible(!dropdownVisible);
    };

    return (
        <div className="DropdownMenuWrapper" onClick={toggleDropdown}>
            <span>Hello, user</span>
            {dropdownVisible && (
                <div className="Dropdown-menu">
                    <Link to="/history">History</Link>
                    <button onClick={handleLogout}>Logout</button>
                </div>
            )}
        </div>
    )
}