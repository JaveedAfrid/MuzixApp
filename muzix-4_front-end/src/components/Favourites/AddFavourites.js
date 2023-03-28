const Favourites = {
    AddFavourites(email, trackId) {
        console.log("Fetching");
        fetch(`http://localhost:8765/api/v1/favourites/add_favourites`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            },
            body: JSON.stringify({ email, trackId })
        })
        console.log(email);
        console.log(trackId);
        console.log("Adding Done");
    }
}

export default Favourites;