import React, { useState, useEffect } from 'react'
import DisplayTracks from './DisplayTracks';

export default function Tracks(props) {

    const [tracks, setTracks] = useState([])

    useEffect(() => {
        fetch('https://api.napster.com/v2.1/tracks/top?apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0&limit')
            .then(res => res.json())
            .then(data => setTracks(data.tracks));

    }, [])

    return (
        <div>
            <div data-testid="containerClass" className="container">
                <h1 id="tracks" data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">TRACKS</h1>
                <div data-testid="rowClass" className="row row-cols-1 row-cols-md-4 g-4 mt-1 mr-2 ml-2">
                    {
                        tracks.map(item => <DisplayTracks key={item.id} id={item.id} name={item.name} albumId={item.albumId} previewURL={item.previewURL} email={props.email} />)
                    }
                </div>
            </div>
        </div>
    )
}
