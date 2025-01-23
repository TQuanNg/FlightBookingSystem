import React,  { useState, useEffect }  from "react";
import { Link, useNavigate, useResolvedPath } from 'react-router-dom';
import { DropdownMenu } from './DropdownMenu';

export default function NavBar() {
    const [isLoggedIn, setLoggedIn] = useState(false);
    const navigate = useNavigate();   
    
    useEffect(() => {
        const token = localStorage.getItem('token');
        setLoggedIn(!!token);

        const handleStorageChange = () => {
            const token = localStorage.getItem('token');
            setLoggedIn(!!token);
        };

        window.addEventListener('storage', handleStorageChange);

        return () => {
            window.removeEventListener('storage', handleStorageChange);
        };
    })

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        localStorage.removeItem('bookingDetails');
        setLoggedIn(false);
        navigate('login');
    }

    return (
        <nav className="NavBar">
            <Link to="/" className='dsad'>
                Home
            </Link>
            <div className="navbar-title">Flight Booking System</div>
            <ul>
                <Link to="/history">History</Link>
                {!isLoggedIn ? (
                    <Link to="/login">Login</Link>
                ): (

                        <DropdownMenu handleLogout={handleLogout} />
                )}
                <Link to="/about">About</Link>
                <Link to="/cart">Cart</Link>
            </ul>
        </nav>
    )
}
/*
export const CustomLink = ({ to, children, ...props }) => {
    const resolvedPath = useResolvedPath(to) // ensure this is abs path
    const isActive = useMatch({ path: resolvedPath.pathname, end: true}) // end ensure abs path match

    return (
        <li className={isActive ? "activeBar" : ""}>
            <Link to={to} {...props}>
                {children}
            </Link>
        </li>
    )
}*/