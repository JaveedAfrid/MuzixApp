import React, { useState } from 'react';
import { Link, useHistory, BrowserRouter as Router } from 'react-router-dom';

export default function Login() {

    const history = useHistory();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    let authToken = '';

    const LoginHandler = () => {
        fetch("http://localhost:8765/api/v1/user/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        }).then(res => res.text())
            .then(data => {
                authToken = data;
                localStorage.setItem('authToken', authToken);
                localStorage.setItem('email', email)
                if (authToken !== "Incorrect Email" && authToken !== "Incorrect password") {
                    history.push(`/dashboard`)
                } else if (email === '' && password === '') {
                    setError("Enter user credentials")
                    localStorage.clear();
                }
                else if (authToken === "Incorrect Email") {
                    setError("Incorrect Email")
                    localStorage.clear();
                    console.log(data);
                } else if (authToken === "Incorrect password") {
                    setError("Incorrect password")
                    localStorage.clear()
                    console.log(data);
                }
            }).catch((e) => {
                setError("Invalid username or password")
            })
    }
    // position-absolute top-50 start-50 translate-middle 
    return (
        <div className="loginComp container-fluid">
            <div className="row">
                <div className="col-md-4 ml-auto m-5 p-5 mt-5">
                    <h2 id="login" data-testid="headerId" className="text-secondary text-center">Login Here</h2>
                    {
                        error !== '' ? <div className="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>{error} !</strong>
                            <button type="button" className="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div> : <div></div>
                    }
                    <div className="mb-2">
                        <input id="username" type="email" onChange={(e) => setEmail(e.target.value)} className="form-control" placeholder="Username" />
                    </div>
                    <div className="mb-2">
                        <input id="password" type="password" onChange={(e) => setPassword(e.target.value)} className="form-control" placeholder="Password" />
                    </div>
                    <div className="mb-2">
                        <button id="btnLogin" className="btn border border-secondary" onClick={LoginHandler} >Login</button>
                        {/* <Router> */}
                        <Link id="btnRegister" to="/register" className="float-end btn border border-secondary">Register</Link>
                        {/* </Router> */}
                    </div>
                </div>
            </div>
        </div>
    )
}
