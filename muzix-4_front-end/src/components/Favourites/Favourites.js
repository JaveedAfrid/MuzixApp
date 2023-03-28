import React, { useState, useEffect } from 'react'
import DisplayFavourites from './DisplayFavourites';

export default function Favourites() {
    const email = localStorage.getItem('email')
    const [tracks, setTracks] = useState([]);
    var trackList = [];

    useEffect(() => {
        fetch(`http://localhost:8765/api/v1/favourites/favourites/${email}`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            }
        })
            .then(res => res.json())
            .then(data => {
                trackList = data;
                getTracks();
            });
    }, [])

    function getTracks() {
        fetch(`http://api.napster.com/v2.2/tracks/${trackList}?apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0`)
            .then(res => res.json())
            .then(data => setTracks(data.tracks))
    }

    return (
        <div data-testid="containerClass" className="container">
            <h1 id="fav" data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">FAVOURITES</h1>
            <div data-testid="rowClass" className="row row-cols-1 row-cols-md-4 g-4 mt-1 mr-2 ml-2">
                {
                    tracks.map(item => <DisplayFavourites key={item.id} id={item.id} name={item.name} albumId={item.albumId} previewURL={item.previewURL} email={email} />)
                }
            </div>
        </div>
    )
}