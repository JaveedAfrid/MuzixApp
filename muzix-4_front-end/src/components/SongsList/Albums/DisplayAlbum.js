import React from 'react'
import { Link, BrowserRouter as Router, Route } from 'react-router-dom';

export default function DisplayAlbum(props) {

    return (
        <div className="container">
            <div className="col">
                <div data-testid="cardTag" className="card background border border-primary">
                    {/* <Router> */}
                        <Link to={`/album/tracks/${props.id}`}><img data-testid="imageTag" src={`http://direct.rhapsody.com/imageserver/v2/albums/${props.id}/images/300x300.jpg`} className="card-img-top" alt="..." /></Link>
                    {/* </Router> */}
                    <div className="card-body">
                        <h6 className="card-title text-center">{props.artistName}</h6>
                    </div>
                </div>
            </div>
        </div>
    )
}
