import React, {useState, useEffect} from 'react';
import Tabs from './Tabs';
import { useNavigate} from 'react-router-dom';

export const HomePage = () => {

    return(
        <div className='Home'>
            <Tabs />
        </div>
    )
}