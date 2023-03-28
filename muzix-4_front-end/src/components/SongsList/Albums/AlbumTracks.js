import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import DisplayAtracks from './DisplayAtracks';

export default function AlbumTracks() {
    let { id } = useParams();
    const email = localStorage.getItem('email');
    const [tracks, setTrack] = useState([]);

    useEffect(() => {
        fetch(`https://api.napster.com/v2.2/albums/${id}/tracks?apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0`)
            .then(res => res.json())
            .then(data => setTrack(data.tracks));

    }, [])

    return (
        <div data-testid="containerClass" className="container">
            <h1 data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">TRACK LIST</h1>
            <div data-testid="rowClass" className="row row-cols-1 row-cols-md-4 g-4 mt-1 mr-2 ml-2">
                {
                    tracks.map(item => <DisplayAtracks key={item.id} id={item.id} name={item.name} albumId={item.albumId} previewURL={item.previewURL} email={email} />)
                }
            </div>
        </div>
    )
}
