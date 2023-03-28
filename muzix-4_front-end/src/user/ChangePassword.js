import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom';
export default function ChangePassword() {

    const [oldPassword, setOldPassword] = useState('');
    const [currentPass, setcurrentPass] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPass, setConfirmPass] = useState('');
    const email = localStorage.getItem('email')
    const history = useHistory();

    useEffect(() => {

        fetch(`http://localhost:8765/api/v1/user/getPassword/${email}`, {
        }).then(res => res.text())
            .then(data => setOldPassword(data))
    }, [])
    function UpdatePassword() {
        console.log(password);
        if (oldPassword === currentPass && password === confirmPass && password !== '') {
            console.log("Correct password");
            fetch(`http://localhost:8765/api/v1/user/updatePassword`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            }).then((res) => {
                if (!res.ok) {
                    throw Error('Error')
                }
                history.push("/dashboard")
                return res.json();
            }).catch((e) => console.log(e.message))
        } else {
            console.log("False");
        }
    }



    return (
        <div className="mt-5 text-white">
            <h3 data-testid="headerId" className="text-center">Change Password</h3>
            <div className="row mt-5">
                <div className="col-md-4 offset-md-4">
                    <label class="form-label">Current Password</label>
                    <input type="text" onChange={(e) => setcurrentPass(e.target.value)} class="form-control" />
                </div>
            </div>

            <div className="row">
                <div className="col-md-4 offset-md-4">
                    <label class="form-label">New Password</label>
                    <input type="text" onChange={(e) => setPassword(e.target.value)} class="form-control" />
                </div>
            </div>

            <div className="row">
                <div className="col-md-4 offset-md-4">
                    <label class="form-label">Confirm Password</label>
                    <input type="text" onChange={(e) => setConfirmPass(e.target.value)} class="form-control" />
                </div>
            </div>

            <div className="row mt-3">
                <div className="col-md-4 offset-md-4">
                    <button type="button" onClick={UpdatePassword} class="btn btn-success">Change Password</button>
                </div>
            </div>
        </div>
    )
}
