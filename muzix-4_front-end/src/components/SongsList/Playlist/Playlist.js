import React, { useState, useEffect } from 'react'
import DisplayPlaylist from './DisplayPlaylist';

export default function Playlist() {

    const [playlist, setPlaylist] = useState([])

    useEffect(() => {
        fetch('http://api.napster.com/v2.2/playlists?pretty=true&apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0&limit=15')
            .then(res => res.json())
            .then(data => setPlaylist(data.playlists));

    }, [])

    return (
        <div>
            <div data-testid="containerClass" className="container">
                <h1 id="playlist" data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">PLAYLIST</h1>
                <div data-testid="rowClass" className="row row-cols-1 row-cols-md-5 g-4 mt-1 mr-2 ml-2">
                    {
                        playlist.map(item => <DisplayPlaylist key={item.id} id={item.id} name={item.name} />)
                    }
                </div>
            </div>
        </div>
    )
}
