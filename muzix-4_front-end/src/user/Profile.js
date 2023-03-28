import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { BrowserRouter as Router} from 'react-router-dom'

export default function Profile() {
    const [userDetails, setUserDetails] = useState([]);
    const email = localStorage.getItem('email');
    var image = '';
    useEffect(() => {
        fetch(`http://localhost:8765/api/v1/user/getProfile/${email}`)
            .then(res => res.json())
            .then(data => {
                setUserDetails(data)
                // var base64Flag = 'data:image/jpeg;base64,';
                // var imageStr = arrayBufferToBase64(data.image)
                // image = base64Flag + imageStr;
            })
        console.log(userDetails);
    }, [])
    // function arrayBufferToBase64(buffer) {
    //     var binary = '';
    //     var bytes = [].slice.call(new Uint8Array(buffer));
    //     bytes.forEach((b) => binary += String.fromCharCode(b));
    //     return window.btoa(binary);
    // };
    return (
        <div className="text-white">
            <h2 id="profile" className="text-center">My Profile</h2>
            <div className="offset-md-2 mt-3">
                <img className="rounded-circle" src={image} alt="" width="150" height="150" />
                {/* <Router> */}
                    <Link className="offset-md-1 text-secondary" to="/confirmpassword"><i class="fas fa-unlock-alt"></i> Change password</Link>
                    <div>
                        <Link id="editProfile" data-testid="edit" className="offset-md-0 mt-3 text-secondary" to="/editProfile"><i class="fas fa-edit"></i>Edit Profile</Link>
                    </div>
                {/* </Router> */}
            </div>
            <div className="row mr-0">
                <div className="col-md-2 offset-md-2">
                    <label class="form-label">First Name</label>
                    <input id="fname" type="text" class="form-control" disabled value={userDetails.fname} />
                </div>
                <div className="col-md-2 offset-md-2 ml-5">
                    <label class="form-label">Last Name</label>
                    <input id="lname" type="text" class="form-control" disabled value={userDetails.lname} />
                </div>
            </div>

            <div className="row mr-0">
                <div className="col-md-2 offset-md-2">
                    <label class="form-label">Email</label>
                    <input id="email" type="email" class="form-control" disabled value={userDetails.email} />

                </div>
                <div className="col-md-2 offset-md-2 ml-5">
                    <label class="form-label">Gender</label>
                    <input id="gender" type="text" class="form-control" disabled value={userDetails.gender} />
                </div>
            </div>

            <div className="row mr-0">
                <div className="col-md-2 offset-md-2">
                    <label class="form-label">DOB</label>
                    <input id="dob" type="date" class="form-control" disabled value={userDetails.dob} />
                </div>
                <div className="col-md-2 offset-md-2 ml-5">
                    <label class="form-label">Age</label>
                    <input id="age" type="number" class="form-control" disabled value={userDetails.age} />
                </div>
            </div>

            <div className="row mr-0">
                <div className="col-md-2 offset-md-2">
                    <label class="form-label">Phone</label>
                    <input id="phone" type="number" class="form-control" disabled value={userDetails.phone} />
                </div>

            </div>
        </div>

    )
}
