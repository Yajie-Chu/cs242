import React, { Component } from 'react';
import './App.css';
import queryString from 'query-string'; 

// set SPOTIFY_CLIENT_ID=6f2c8665b3fc4ed497b744ea39955d26 id
// set SPOTIFY_CLIENT_SECRET=4a9ae37d5a414b2c9bb6620d714e9e6e secret

let hardCodedData ={
  user:{
    name: 'Aldo',
    playlist: [
      {
        name:'My favorites',
        songs: [
          {name: 'Hello World', duration: 1230}, 
          {name: 'Do not leave me', duration: 1211}, 
          {name: 'Rossa', duration:7299}
        ]
      },
      {
        name: 'Discover weekly',
        songs:[
          {name: 'Le song', duration: 2121}, 
          {name: 'The song', duration: 3321}, 
          {name: 'Slide', duration: 8992}
        ]
      },
      {
        name: 'Rappish',
        songs:[
          {name: 'Eminem', duration: 3121}, 
          {name: 'Not afraid', duration: 8821}
        ]
      }
    ]
  }
}

class AggregatePlaylist extends Component{
  render(){
    return(
      <div style={{width:"40%", display: 'inline-block'}} className="Aggregate">
        <h2> {this.props.playlists.length} playlists </h2>
      </div>
    );
  }
}

class AggregateHour extends Component{
  render(){
    let allSongs = this.props.playlists.reduce((songs, eachPlaylist) =>{
      return songs.concat(eachPlaylist.songs)
    }, [])
    let totalDuration = allSongs.reduce((sum, eachSong)=>{
      return sum + eachSong.duration
    }, 0)

    return(
      <div style={{width:"40%", display: 'inline-block'}} className="Aggregate">
        <h2> {Math.round(totalDuration / 3600)} hours </h2>
      </div>
    );
  }
}

class Search extends Component{
  render(){
    return(
      <div>
        <img/>
        <input type="text" onKeyUp = {event => this.props.onTextChange(event.target.value)}/>
        Filter
      </div>
    );
  }
}

class Playlist extends Component{
  render(){
    return(
      <div style={{width: "25%", display: 'inline-block'}}>
        <img src={this.props.playlist.imageUrl} style={{width: '160px'}}/>
        <h3>{this.props.playlist.name}</h3>
        <ul>
          {this.props.playlist.songs.map(song =>
            <li>{song.name}</li>
          )}
        </ul>
      </div>
    );
  }
}

class Pl extends Component {
  constructor(){
    super();
    this.state = {
      serverData: {},
      filterString: ''
    }
  }

  componentDidMount(){
    let parsed = queryString.parse(window.location.search);
    let accessToken = parsed.access_token;
    console.log('after this parse')
    console.log(parsed)
    console.log('no parse hello')

    if(!accessToken)
      return;


    fetch('https://api.spotify.com/v1/me', {
      headers: {'Authorization': 'Bearer ' + accessToken}
    }).then(response => response.json())
    .then(data => this.setState({
      user: {
          name: data.display_name
      }
    }))

    fetch('https://api.spotify.com/v1/me/playlists', {
      headers: {'Authorization': 'Bearer ' + accessToken}
    }).then(response => response.json())
    .then(playlistData => {
      let playlists = playlistData.items
      console.log(playlists)
      console.log('hello')
      let trackDataPromises = playlists.map(playlist => {
        let responsePromise = fetch(playlist.tracks.href, {
          headers: {'Authorization': 'Bearer ' + accessToken}
        })
        let trackDataPromise = responsePromise
          .then(response => response.json())
        return trackDataPromise
      })
      let allTracksDataPromises = Promise.all(trackDataPromises)
      let playlistsPromise = allTracksDataPromises.then(trackDatas => {
        trackDatas.forEach((trackData, i) => {
          playlists[i].trackDatas = trackData.items
          .map(item => item.track)
          .map(trackData => ({
            name: trackData.name,
            duration: trackData.duration_ms / 1000
          }))
        })
        return playlists
      })
      return playlistsPromise
    })
    .then(playlists => this.setState({
      playlists: playlists.map(item => {
        console.log(item.trackDatas)
        return{
          name:item.name,
          imageUrl: item.images[0].url,
          songs:item.trackDatas.slice(0,3) 
        } 
      })     
    }))
  }

  render() {
    let playlistResult = 
      this.state.user &&
      this.state.playlists ?
      this.state.playlists.filter(playlist => {
        let matchesPlaylist = playlist.name.toLowerCase().includes(
          this.state.filterString.toLowerCase())
        let matchesSong = playlist.songs.find(song => song.name.toLowerCase()
          .includes(this.state.filterString.toLowerCase()))
        return matchesPlaylist || matchesSong

        
      }) : []
    
    return (
       <div className="App">
         {this.state.user ? 
         <div>
           <h1> {this.state.user.name}'s playlists </h1>
    
           <AggregatePlaylist playlists={playlistResult}/>
           <AggregateHour playlists={playlistResult}/>
         
           <Search onTextChange={text => this.setState({filterString: text})}/>
           {playlistResult.map(playlist =>
              <Playlist playlist={playlist}/>   
           )}
        
         </div> : <button onClick={() => {
            window.location = window.location.href.includes('localhost') 
              ? 'http://localhost:8888/login'
              : 'https://spoticool-backend.herokuapp.com/login' }
          }

           style={{'marginTop':'20px', padding: '20px', 'font-size':'50px'}}> Sign in with spotify </button>
         }
       </div>
    );
  }
}

export default Pl;
