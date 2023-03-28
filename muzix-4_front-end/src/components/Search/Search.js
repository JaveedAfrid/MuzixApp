import React, { useContext } from 'react'
import AppContext from '../../AppContext';
import DisplaySearchAlbums from "./DisplaySearchAlbums";

export default function Search(props) {

    const { state } = useContext(AppContext)

    console.log(state);
    return (
        <div data-testid="containerClass" className="container">
            <h1 id="searchTitle" data-testid="headerId" className="text-center mt-4 mb-3 text-warning shadow-lg p-2 mb-4 rounded headBackground">ALBUMS</h1>
            <div data-testid="rowClass" className="row row-cols-1 row-cols-md-5 g-4 mt-1 mr-2 ml-2">
                {
                    state.map(item => <DisplaySearchAlbums key={item.id} id={item.id} name={item.name} artistName={item.artistName} />)
                }
            </div>
        </div>
    )
}