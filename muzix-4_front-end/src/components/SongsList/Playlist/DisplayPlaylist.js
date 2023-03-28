import React from 'react'
import { Link, BrowserRouter as Router, Route } from 'react-router-dom';

export default function DisplayPlaylist(props) {
    return (
        <div className="container">
            <div className="col">
                <div data-testid="cardTag" className="card background border border-primary">
                    {/* <Router> */}
                        <Link to={`/playlist/tracks/${props.id}`}><img data-testid="imageTag" src={`http://direct.napster.com/imageserver/v2/playlists/${props.id}/artists/images/230x153.jpg`} className="card-img-top" alt="..." /></Link>
                    {/* </Router> */}
                    <div className="card-body">
                        <h6 className="card-title text-center">{props.name}</h6>
                    </div>
                </div>
            </div>
        </div>
    )
}
