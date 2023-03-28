import React, { useState, useEffect } from 'react'
import DisplayAlbum from './DisplayAlbum';

export default function Album() {
    const [album, setAlbum] = useState([])

    useEffect(() => {
        fetch('http://api.napster.com/v2.2/albums/top?pretty=true&apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0&limit=15')
            .then(res => res.json())
            .then(data => setAlbum(data.albums));

    }, [])


    return (
        <div data-testid="containerClass" className="container">
            <h1 id="album" data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">ALBUMS</h1>
            <div data-testid="rowClass" className="row row-cols-1 row-cols-md-5 g-4 mt-1 mr-2 ml-2">
                {
                    album.map(item => <DisplayAlbum key={item.id} id={item.id} name={item.name} artistName={item.artistName} />)
                }
            </div>
        </div>
    )
}
