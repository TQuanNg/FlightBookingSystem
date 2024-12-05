import React, { useState, useEffect } from 'react';
import { Link, useNavigate} from 'react-router-dom';
//import { useNavigate } from "react-router";


export const LoginPage = () => {
    const [form, setForm] = useState('Login');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [user, setUser] = useState();
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate(); 

    const handleLogin = async (e) => {
        e.preventDefault();

        if (!username || !password) {
            //alert("Both username and password are required.");
            setErrorMessage("Both username and password are required.");
            return;
        }

        setErrorMessage("");
        

        try {
            const response = await fetch(`http://localhost:8080/api/users/login?username=${username}&passwordHash=${password}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password }),
            });
    
            if (!response.ok) {
                const errorData = await response.json();
                setErrorMessage(errorData.message || 'Login failed. Please try again.');
            } else {
                const data = await response.json();
                if (data.data && data.data.token) {
                    localStorage.setItem('token', data.data.token);
                    localStorage.setItem('user', JSON.stringify(data.data));
                    setUser(data.data);

                    setUsername("");
                    setPassword("");

                    //localStorage.setItem('token', data.token)
                    navigate('/');
                } else {
                    setErrorMessage('Login failed. Please try again.');
                }
            }
        } catch (error) {
            console.error("Network error:", error); // Log the error
            setErrorMessage("Unable to connect to the server. Please try again later.");
        }
    }

    return (
        <div className='LoginWrapper'>
            <h2>Login</h2>
            <form >
                <div className="Login">
                    <input type="text" className="TripInput" placeholder='Enter your username'
                        onChange={(e) => setUsername(e.target.value)}
                        required />

                    <input type="text" className="TripInput" placeholder='Enter your passwprd'
                        onChange={(e) => setPassword(e.target.value)}
                        required />

                </div>
                <button onClick={handleLogin}>Log In</button>
            </form>
            <p>Don't have an account?</p>
            <Link to="/signup">Sign up</Link>

            {errorMessage && <p className="error-message">{errorMessage}</p>}
        </div>
    )
}