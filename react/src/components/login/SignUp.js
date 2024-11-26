import React, {useState} from 'react';

export const SignUpPage = () => {
    const [form, setForm] = useState('Login');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [reEnterpassword, setreEnterpassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    

    return(
        <div className='SignUpWrapper'>
            <h2>Sign up/Register</h2>
            <form >
                <div className="Login">
                    <input type="text" placeholder='Enter your email'
                    onChange={(e) => setEmail(e.target.value)} 
                    required />

                    <input type="text" placeholder='Enter your passwprd'
                    onChange={(e) => setPassword(e.target.value)} 
                    required />
                    
                    <input type="text" placeholder='Re enter your passwprd'
                    onChange={(e) => setreEnterpassword(e.target.value)} 
                    required />
                    
                </div>
                <button onClick={handleSignUp}>Log In</button>
            </form>
        </div>
    )
}