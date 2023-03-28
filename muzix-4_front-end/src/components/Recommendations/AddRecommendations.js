const Recommendations = {
    AddRecommendation(trackId) {
        console.log("Fetching");
        fetch(`http://localhost:8765/api/v1/recommend/tracks`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            },
            body: JSON.stringify({ trackId })
        })
        console.log(trackId);
        console.log("Adding Done");
    }
}

export default Recommendations;