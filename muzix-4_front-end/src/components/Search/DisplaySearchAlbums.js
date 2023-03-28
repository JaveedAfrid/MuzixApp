import React from 'react'
import { Link } from 'react-router-dom'
import { BrowserRouter as Router } from 'react-router-dom'



export default function DisplaySearchAlbums(props) {
    return (
        <div className="container">
            <div className="col">
                <div data-testid="cardTag" className="card background border border-primary">
                    {/* <Router> */}
                    <Link to={`/SearchedTracks/${props.id}`}><img data-testid="imageTag" src={`http://direct.rhapsody.com/imageserver/v2/albums/${props.id}/images/300x300.jpg`} className="card-img-top" alt="..." /></Link>
                    {/* </Router> */}
                    <div className="card-body text-white">
                        <h6 className="card-title text-center">{props.name}</h6>
                    </div>
                </div>
            </div>
        </div>
    )
}