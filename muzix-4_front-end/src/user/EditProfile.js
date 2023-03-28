import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom';

export default function EditProfile() {

    const email = localStorage.getItem('email')
    const history = useHistory();
    const [fname, setFname] = useState('')
    const [lname, setLname] = useState('')
    const [gender, setGender] = useState('')
    const [phone, setPhone] = useState('')
    const [dob, setDob] = useState('')
    const [age, setAge] = useState('')

    const [firsterror, setFirstError] = useState('')
    const [lasterror, setLastError] = useState('')

    useEffect(async () => {
        console.log("useeffect");
        await fetch(`http://localhost:8765/api/v1/user/getProfile/${email}`)
            .then(res => res.json())
            .then(data => {
                setFname(data.fname);
                setLname(data.lname);
                setGender(data.gender);
                setPhone(data.phone);
                setDob(data.dob);
                setAge(data.age);
            })
        console.log(fname);
        console.log(lname);
        console.log(gender);
        console.log(phone);
        console.log(dob);
        console.log(age);
    }, [])


    async function updateProfile() {
        if (fname != '' && lname != '') {
            await fetch(`http://localhost:8765/api/v1/user/update`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, fname, lname, gender, phone, dob, age })
            })
            console.log(email);
            console.log(fname);
            console.log(lname);
            console.log(gender);
            console.log(phone);
            console.log(dob);
            console.log(age);
            history.push("/profile");
        }
         if(fname=='') {
            
            setFirstError("First name is mandatory")
        }
         if(lname==''){
            setLastError("Last name is mandatory")
        }

    }
    return (
        <div className="text-white">
            <div className="offset-md-2 mt-3">
                <img className="rounded-circle" src="https://img1.hotstarext.com/image/upload/f_auto,t_hcdl/sources/r1/cms/prod/9539/649539-h" alt="" width="150" height="150" />
            </div>
            <div className="row">
                <div className="col-md-2 offset-md-2">
                   
                    <label class="form-label">First Name  <span className="text-danger">*</span></label>
                    <input id="fname" type="text" value={fname} onChange={(e) => setFname(e.target.value)} class="form-control" />
                    {
                        firsterror !== '' ? <div className="text-danger">{firsterror}</div>:<div></div>
                    }
                </div>
                <div className="col-md-2 offset-md-2 ml-5">
                    <label class="form-label">Last Name  <span className="text-danger">*</span></label>
                    <input id="lname" type="text" value={lname} onChange={(e) => setLname(e.target.value)} class="form-control" />
                    {
                        lasterror !== '' ? <div className="text-danger">{lasterror}</div>:<div></div>
                    }
                </div>
            </div>
            <div className="row">
                
                <div className="col-md-2 mt-2 w-100 offset-md-2">
                <label class="form-label">Gender</label>
                    <div class="dropdown">
                        <button id="gender" class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            {gender}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><a class="dropdown-item" onClick={(e) => {
                                setGender("Male");
                            }}>Male</a></li>
                            <li><a id="female" class="dropdown-item" onClick={(e) => {
                                setGender("Female");
                            }}>Female</a></li>
                            <li><a class="dropdown-item" onClick={(e) => {
                                setGender("Others")
                            }}>Others</a></li>
                        </ul>
                    </div>
                </div>
                <div className="col-md-2 offset-md-2 ml-5">
                    <label class="form-label">Phone</label>
                    <input id="phone" type="number" value={phone} onChange={(e) => setPhone(e.target.value)} class="form-control" />
                </div>
            </div>
            <div className="row">
                <div className="col-md-2 offset-md-2">
                    <label class="form-label">DOB</label>
                    <input id="dob" type="date" value={dob} onChange={(e) => setDob(e.target.value)} class="form-control" />
                </div>
                <div className="col-md-2 offset-md-2 ml-5">
                    <label class="form-label">Age</label>
                    <input id="age" type="number" value={age} onChange={(e) => setAge(e.target.value)} class="form-control" />
                </div>
            </div>
            <div className="row">
                <div className="col-md-3 offset-md-3">
                    <button id="updateBtn" className="btn btn-success mt-2" onClick={updateProfile}>Update</button>
                </div>
            </div>
        </div>
    )
}
