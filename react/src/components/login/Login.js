import React, {useState, useEffect} from 'react';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import axios from 'axios';


export const LoginPage = () => {
    const [form, setForm] = useState('Login');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        setEmail("");
        setPassword("");

        const response = await fetch(`http://localhost:8001/login?username=${email}&passwordHash=${password}`, {
            method: 'POST',
            headers: {'Content-Type':'application/JSON'},
            body: JSON.stringify({email, password}),
        });

        if(!response.ok) {
            const errorData = await response.json();
                setErrorMessage(errorData.message || 'Login failed. Please try again.');
        }
        else {
            const data = await response.json();

            if (data.data && data.data.token) {
                localStorage.setItem('token', data.data.token);  // Store JWT token in localStorage
                Navigate('/');  // Redirect to the homepage or wherever you want after login
            } else {
                setErrorMessage('Login failed. Please try again.');
            }

            //localStorage.setItem('token', data.token)

            Navigate('/')
        }


    }

    return(
        <div className='LoginWrapper'>
            <h2>Login</h2>
            <form >
                <div className="Login">
                    <input type="text" placeholder='Enter your email'
                    onChange={(e) => setEmail(e.target.value)} 
                    required />
                    
                    <input type="password" placeholder='Enter your passwprd'
                    onChange={(e) => setPassword(e.target.value)} 
                    required />
                    
                </div>
                <button onClick={handleLogin}>Log In</button>
            </form>
            <p>Don't have an account?</p>
            <Link to="/signup">Sign up</Link>
        </div>
    )
}