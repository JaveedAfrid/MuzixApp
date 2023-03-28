import React, { useContext, useState } from 'react'
import { Link, useHistory } from 'react-router-dom';
import musiclogo from '../../images/musiclogo.jpg';
// import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom'
import AppContext from '../../AppContext';

export default function Header(props) {

    const { dispatch } = useContext(AppContext)
    const [searchValue, setSearchValue] = useState('');
    const history = useHistory();

    const empty = () => {
        localStorage.clear();
    }

    async function searchByText() {
        if (searchValue !== '') {
            await fetch(`http://api.napster.com/v2.2/search/verbose?apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0&query=${searchValue}`)
                .then(res => res.json())
                .then(data => dispatch({ type: 'ADD', value: data.search.data.albums }))
            history.push(`/search`)
        } else {
            console.log("Type");
        }

    }

    return (
        <nav data-testid="header" className="navbar navbar-expand-lg navbar-dark bg-dark p-0">
            <div className="container-fluid">
                <div className="container">
                    <a className="navbar-brand" href="#">
                        <img src={musiclogo} className="mr-3" alt="" width="30" height="24" />
                        <p id="appName">MUZIX</p>
                    </a>
                </div>
                <button data-testid="toggleBtn" className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    {/* <Router> */}

                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item d-flex mr-5">
                                <input id="search" onChange={(e) => setSearchValue(e.target.value)} className="form-control-sm mt-1 border-0" type="search" placeholder="eg. Ed Sheeran" aria-label="Search" />
                                <i id="searchbtn" onClick={searchByText} className="fas fa-search text-center pb-0 mt-1 btn btn-primary mr-5"></i>
                                {/* <Link to="/displaySearchAlbums"><i onClick={searchByText} class="fas fa-search text-center pb-0 mt-1 btn btn-primary mr-5"></i></Link> */}
                            </li>
                            <li className="nav-item">
                                <Link to="/dashboard" className="nav-link active" aria-current="page" >Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/favourites" >Favourites</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/recommend" >Recommendations</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/profile">Profile</Link>
                            </li>
                        </ul>
                        <ul className="navbar-nav mb-2 mb-lg-0">
                            <li className="nav-item d-flex">
                                <Link id="logout" onClick={empty} className="nav-link me-auto" to="/">Logout</Link>
                            </li>
                            <li className="nav-item d-flex">
                                <a className="nav-link me-auto" href="#">{props.fname}</a>
                            </li>
                        </ul>
                    {/* </Router> */}
                </div>
            </div>
        </nav>
    )
}