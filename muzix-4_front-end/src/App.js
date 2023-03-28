import { useReducer, useState } from 'react';
import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom'
import Dashboard from './components/Dashboard/Dashboard';
import Favourites from './components/Favourites/Favourites';
import Header from './components/Header/Header';
import NotFound from './components/notfound/NotFound';
import AlbumTracks from './components/SongsList/Albums/AlbumTracks';
import PlaylistTracks from './components/SongsList/Playlist/PlaylistTracks';
import EditProfile from './user/EditProfile';
import Login from './user/Login';
import Register from './user/Register';
import Profile from './user/Profile';
import ChangePassword from './user/ChangePassword';
import AppContext from './AppContext';
import reducer, { initialState } from './AppReducer';
import Recommendations from './components/Recommendations/Recommendations';
import Search from './components/Search/Search';
import SearchAlbumTracks from './components/Search/SearchAlbumTracks';

function App() {
  const [state, dispatch] = useReducer(reducer, initialState)

  // const [fnames, setFnames] = useState('');
  // const [email, setEmail] = useState('');
  // function fnameFunction(setName) {
  //   setFnames(setName);
  // }
  // function removeFunction(empty) {
  //   setFnames(empty);
  // }
  // function userEmailId(uemail) {
  //   setEmail(uemail)
  // }
  return (
    <div>
      <AppContext.Provider value={{ state, dispatch }} >
        <Router>
          <Route component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <Header /> : <Redirect to="/" />} />
          <Switch>
            <Route exact path="/" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/dashboard" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <Dashboard /> : <Redirect to="/" />} />
            <Route exact path="/album/tracks/:id" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <AlbumTracks /> : <Redirect to="/" />} />
            <Route exact path="/playlist/tracks/:id" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <PlaylistTracks /> : <Redirect to="/" />} />
            <Route exact path="/favourites" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <Favourites /> : <Redirect to="/" />} />
            <Route exact path="/recommend" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <Recommendations /> : <Redirect to="/" />} />
            <Route exact path="/profile" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <Profile /> : <Redirect to="/" />} />
            <Route exact path="/editProfile" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <EditProfile /> : <Redirect to="/" />} />
            <Route exact path="/confirmpassword" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <ChangePassword /> : <Redirect to="/" />} />
            <Route exact path="/search" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <Search /> : <Redirect to="/" />} />
            <Route exact path="/SearchedTracks/:id" component={() => localStorage.getItem('authToken') !== null && localStorage.getItem('authToken') !== undefined ? <SearchAlbumTracks /> : <Redirect to="/" />} />
            <Route path="*" component={NotFound} />
          </Switch>
        </Router>
      </AppContext.Provider>
    </div>
  );
}

export default App;