const Favourites = {
    DeleteFavourites(email, trackId) {
        console.log("Fetching");
        fetch(`http://localhost:8765/api/v1/favourites/favourites`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            },
            body: JSON.stringify({ email, trackId })
        })
        console.log(email);
        console.log(trackId);
        console.log("Delete Done");
    }
}

export default Favourites;