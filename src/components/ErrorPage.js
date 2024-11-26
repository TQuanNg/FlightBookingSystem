import React from "react";
import { Link } from 'react-router-dom';

export const ErrorPage = () => {

    return(
        <div className='error-page'>
            Errorrrrrr Page
            <Link to="/" >Not found</Link>
        </div>
    )
}