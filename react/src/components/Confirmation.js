import React, { useState, useEffect } from 'react';
import { Link, useNavigate} from 'react-router-dom';

export const ConfirmationPage = () => {

    return(
        <div>
            <h1>Purchase Successfully</h1>
            <Link to="/">Go back to Home</Link>
        </div>
    )
}