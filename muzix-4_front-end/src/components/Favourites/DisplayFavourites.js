import React from 'react';
import Favourites from './DeleteFavourites';

export default function DisplayFavourites(props) {

    const email = props.email;
    const trackId = props.id;
    const lowTrackId = String(trackId).toLowerCase();

    function getTrackId() {
        Favourites.DeleteFavourites(email, lowTrackId);
    }

    return (
        <div className="container">
            <div className="col">
                <div data-testid="cardTag" className="card background border border-primary">
                    <img data-testid="imageTag" src={`http://direct.rhapsody.com/imageserver/v2/albums/${props.albumId}/images/300x300.jpg`} className="card-img-top" alt="..." />
                    <div className="hearticon"><i onClick={getTrackId} className="bi bi-heart-fill position-absolute top-0 end-0 text-black-50 p-2 fs-6"></i></div>
                    <div className="card-body text-white text-center">
                        <h5 className="card-title">{props.name}</h5>
                        <audio data-testid="audioId" controls className="audio" style={{ width: "100%" }}>
                            <source src={`${props.previewURL}`} type="audio/mpeg" />
                        </audio>
                    </div>
                </div>
            </div>
        </div>
    )
}