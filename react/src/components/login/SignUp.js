import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const SignUpPage = () => {
    const [form, setForm] = useState('Login');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [reEnterpassword, setReEnterPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleSignUp = async (e) => {
        e.preventDefault();

        if(!username || !password || !reEnterpassword || !firstName || !lastName) {
            setErrorMessage('All fields are required!')
            return;
        }
        if (password !== reEnterpassword) {
            setErrorMessage('Re enter password does not match')
            return;
        }

        const user = { firstName, lastName, email, username, passwordHash: password  };

        try {
            const response = await fetch('http://localhost:8080/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user)
            });

            const result = await response.json();

            if (response.ok) {

                setErrorMessage('');
                setFirstName('');
                setLastName('');
                setEmail('');
                setPassword('');
                setReEnterPassword('');
                setErrorMessage('Account created!!!!!')
                navigate('/login')
            } else {
                console.log(result);
                setErrorMessage(result.message || 'Registration failed.');
            }

        }
        catch (error) {
            setErrorMessage('An error occurred. Please try again later.');
        }
    }

    return (
        <div className='SignUpWrapper'>
            <h2>Sign up/Register</h2>
            <form >
                <div className="SignUpFields">
                    <input type="text" className="SignUpInput" placeholder='Enter your first name'
                        onChange={(e) => setFirstName(e.target.value)}
                        required />

                    <input type="text" className="SignUpInput" placeholder='Enter your last name'
                        onChange={(e) => setLastName(e.target.value)}
                        required />

                    <input type="text" className="SignUpInput" placeholder='Enter your email'
                        onChange={(e) => setEmail(e.target.value)}
                        required />
                    <input type="text" className="SignUpInput" placeholder='Enter your username'
                        onChange={(e) => setUsername(e.target.value)}
                        required />

                    <input type="text" className="SignUpInput" placeholder='Enter your passwprd'
                        onChange={(e) => setPassword(e.target.value)}
                        required />

                    <input type="text" className="SignUpInput" placeholder='Re enter your passwprd'
                        onChange={(e) => setReEnterPassword(e.target.value)}
                        required />

                    <button onClick={handleSignUp}>Sign Up</button>
                </div>
                

                {errorMessage && <p className="error-message">{errorMessage}</p>}
            </form>
        </div>
    )
}