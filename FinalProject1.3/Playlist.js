import React, { Component } from 'react';
import './App.css';
import queryString from 'query-string'; 

// set SPOTIFY_CLIENT_ID=6f2c8665b3fc4ed497b744ea39955d26 id
// set SPOTIFY_CLIENT_SECRET=4a9ae37d5a414b2c9bb6620d714e9e6e secret

/*
Handles the total playlist aggregate number
*/
class AggregatePlaylist extends Component{
  render(){
    return(
      <div style={{width:"40%", display: 'inline-block'}} className="Aggregate">
        <h2> {this.props.playlists.length} playlists </h2>
      </div>
    );
  }
}

/*
Handles the total Hour aggregate 
*/
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


/*
Handles the filter bar
*/
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

/*
Handles each Playlist metadata 
*/
class Playlist extends Component{
  render(){
    //console.log(this.props.link)
    return(
      <div style={{width: "25%", display: 'inline-block'}}>
        <a href={this.props.playlist.exUrl}><img src={this.props.playlist.imageUrl} style={{width: '160px'}}/></a>
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

/*
Main component of the playlist page
*/
class Pl extends Component {
  /*
  Constructor, runs when rendered. 
  */
  constructor(){
    super();
    this.state = {
      
      
      playlists: [],
      filterString: '',
      accessToken: ''
    }
  }

  /*
  three components that handle API calls and local storage
  */
  componentDidMount(){
    if(localStorage.getItem('accessToken') == null || !localStorage.getItem('accessToken')){
    let parsed = queryString.parse(window.location.search);
    let at = parsed.access_token;
    
    if(!at) return;
   
    this.setState({accessToken: at});
    //console.log(this.accessToken)


    fetch('https://api.spotify.com/v1/me', {
      headers: {'Authorization': 'Bearer ' + at}
    }).then(response => response.json())
    .then(data => this.setState({
      user: {
          name: data.display_name
      }
    }))

   

    fetch('https://api.spotify.com/v1/me/playlists', {
      headers: {'Authorization': 'Bearer ' + at}
    }).then(response => response.json())
    .then(playlistData => {
      let playlists = playlistData.items
      //console.log(playlists)
      //console.log('hello')
      let trackDataPromises = playlists.map(playlist => {
        let externalUrl = playlist.external_urls.spotify
        //console.log(externalUrl)
        let responsePromise = fetch(playlist.tracks.href, {
          headers: {'Authorization': 'Bearer ' + at}
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
        //console.log(item.trackDatas)
        return{
          name:item.name,
          imageUrl: item.images[0].url,
          songs:item.trackDatas.slice(0,3),
          exUrl: item.external_urls.spotify
        } 
      })     
    }))
  }else{

    //if(localStorage.getItem('accessToken') == "") return;
    fetch('https://api.spotify.com/v1/me', {
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
    }).then(response => response.json())
    .then(data => this.setState({
      user: {
          name: data.display_name
      }
    }))

   

    fetch('https://api.spotify.com/v1/me/playlists', {
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
    }).then(response => response.json())
    .then(playlistData => {
      let playlists = playlistData.items
      //console.log(playlists)
      //console.log('hello')
      let trackDataPromises = playlists.map(playlist => {
        let externalUrl = playlist.external_urls.spotify
        //console.log(externalUrl)
        let responsePromise = fetch(playlist.tracks.href, {
          headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
        })
        let trackDataPromise = responsePromise
          .then(response => response.json())
        return trackDataPromise
      })
      console.log(trackDataPromises)
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
        //console.log(item.trackDatas)
        return{
          name:item.name,
          imageUrl: item.images[0].url,
          songs:item.trackDatas.slice(0,3),
          exUrl: item.external_urls.spotify
        } 
      })     
    }))
  }
  }

  componentWillMount(){
    localStorage.getItem('accessToken') && this.setState({accessToken: localStorage.getItem('accessToken')})
  }

  componentWillUpdate(nextProps, nextState){
    localStorage.setItem('accessToken', nextState.accessToken);
  }

  render() {
    //console.log(this.state.playlists)
    // returns the filtered string
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

    
    //console.log(this.state.externalUrl)
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
         </div> : <h1> Oops, please log in to access your spotify </h1>}

       </div>
    );
  }
}

export default Pl;
