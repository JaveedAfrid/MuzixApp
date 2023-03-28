import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import DisplayPtracks from './DisplayPtracks';

export default function PlaylistTracks() {
    let { id } = useParams();
    const email = localStorage.getItem('email')
    const [playlistTrack, setPlaylistTrack] = useState([])

    useEffect(() => {
        fetch(`https://api.napster.com/v2.0/playlists/${id}/tracks?apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0&limit=20`)
            .then(res => res.json())
            .then(data => setPlaylistTrack(data.tracks));

    }, [])

    return (
        <div data-testid="containerClass" className="container">
            <h1 data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">TRACK LIST</h1>
            <div data-testid="rowClass" className="row row-cols-1 row-cols-md-4 g-4 mt-1 mr-2 ml-2">
                {
                    playlistTrack.map(item => <DisplayPtracks key={item.id} id={item.id} name={item.name} albumId={item.albumId} previewURL={item.previewURL} email={email} />)
                }
            </div>
        </div>
    )
}
