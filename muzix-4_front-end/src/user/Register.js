import React, { useState } from 'react'
import { useHistory } from 'react-router-dom';

export default function Register() {

    const history = useHistory();

    const [fname, setFname] = useState('');
    const [lname, setLname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [password1, setPassword1] = useState('');
    const [error, setError] = useState('')

    const RegisterHandler = () => {
        if (password === password1) {
            console.log("Login Successful");
            fetch("http://localhost:8765/api/v1/user/register",
                {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ fname, lname, email, password })
                }
            ).then(res => {
                if (!res.ok) {
                    throw Error(`User Already Exist with given email ${email}`)
                } else {
                    history.push("/");
                }
            }).catch((e) => {
                setError(e.message)
            })
        } else {
            console.log("Password missmatch");
        }
    }
    return (
        <div className="loginComp container-fluid">
            <div className="row">
                <div className="col-md-4 ml-auto m-5 p-5 mt-5">
                    <h2 data-testid="headerId" className="text-center text-secondary">Sign Up</h2>
                    {
                        error !== '' ? <div className="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>{error}!</strong>
                            <button type="button" className="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div> : <div></div>
                    }
                    <div className="mb-2">
                        <input id="fname" type="text" onChange={(e) => setFname(e.target.value)} className="form-control" placeholder="First Name" />
                    </div>
                    <div className="mb-2">
                        <input id="lname" type="text" onChange={(e) => setLname(e.target.value)} className="form-control" placeholder="Last Name" />
                    </div>
                    <div className="mb-2">
                        <input id="email" type="email" onChange={(e) => setEmail(e.target.value)} className="form-control" placeholder="Email" />
                    </div>
                    <div className="mb-2">
                        <input id="pass" type="password" onChange={(e) => setPassword(e.target.value)} className="form-control" placeholder="Password" />
                    </div>
                    <div className="mb-2">
                        <input id="pass1" type="password" onChange={(e) => setPassword1(e.target.value)} className="form-control" placeholder="Confirm Password" />
                    </div>
                    <div className="mb-2">
                        <button id="btnRegister" data-testid="btnLogin" className="btn border border-secondary" onClick={RegisterHandler} >Sign Up</button>
                    </div>
                </div>
            </div>
        </div>

    )
}
