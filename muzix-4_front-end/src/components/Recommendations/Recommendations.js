import React, { useState, useEffect } from 'react'
import DisplayRecommendations from './DisplayRecommendations';

export default function Recommendations() {
    const [tenTracks, setTenTracks] = useState([]);
    const [tenStatus, setTenStatus] = useState('');
    var topTenList = [];

    useEffect(() => {
        fetch(`http://localhost:8765/api/v1/recommend/tracksTen`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            }
        })
            .then(res => res.json())
            .then(data => {
                topTenList = data;
                if (topTenList.length !== 0) {
                    tenTrackHandler();
                } else {
                    console.log("Empty");
                    setTenStatus('No Recommendations');

                }
            });
    }, [])

    function tenTrackHandler() {
        fetch(`http://api.napster.com/v2.2/tracks/${topTenList}?apikey=ZTI3ZDdmZjktODMxOS00ZmM1LTg0Y2UtMTY4OThmOTBiNTA0`)
            .then(res => res.json())
            .then(data => setTenTracks(data.tracks))
    }

    return (
        <div data-testid="containerClass" className="container">
            <h1 id="recommend" data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">TOP 10 RECOMMENDED TRACKS</h1>
            <div data-testid="rowClass" className="row row-cols-1 row-cols-md-4 g-4 mt-1 mr-2 ml-2">
                {
                    tenStatus == '' ? tenTracks.map(item => <DisplayRecommendations key={item.id} id={item.id} name={item.name} albumId={item.albumId} previewURL={item.previewURL} />)
                        : <div className="text-white"> {tenStatus} </div>
                }
            </div>
        </div>
    )
}