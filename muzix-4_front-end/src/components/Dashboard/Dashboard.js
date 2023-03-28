import React from 'react'
import Album from '../SongsList/Albums/Album'
import Playlist from '../SongsList/Playlist/Playlist'
import Tracks from '../SongsList/Tracks/Tracks'

function Dashboard() {
    const email = localStorage.getItem('email')
    return (
        <div className="text-white">
            <div data-testid="carouselExampleFade" className="carousel slide carousel-fade" data-bs-ride="carousel">
                <div className="carousel-inner">
                    <div className="carousel-item active p-1">
                        <img src={require('../../images/carousel1.jpg').default} className="d-block" width="100%" height="400" alt="..." />
                    </div>
                    <div className="carousel-item p-1">
                        <img src={require('../../images/carousel2.jpg').default} className="d-block" width="100%" height="400" alt="..." />
                    </div>
                    <div className="carousel-item p-1">
                        <img src={require('../../images/carousel3.jpg').default} className="d-block" width="100%" height="400" alt="..." />
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span data-testid="pre" className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span data-testid="next" className="visually-hidden">Next</span>
                </button>
            </div>
            {/* Display Albums */}
            <Album />
            {/* Display Playlist */}
            <Playlist />
            {/* Display tracks */}
            <Tracks email={email} />
        </div>
    )
}

export default Dashboard;
