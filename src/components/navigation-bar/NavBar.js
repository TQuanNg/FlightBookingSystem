import React from "react";
import { Link, useMatch, useResolvedPath } from 'react-router-dom';

export default function NavBar() {

    

    return (
        <nav className="NavBar">
            <Link to="/" className='dsad'>
                Home
            </Link>
            <ul>
                <Link to="/history">History</Link>
                <Link to="/login">Login</Link>
                <Link to="/about">About</Link>
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