import React, {Component} from 'react';
import './App.css'; 

// 4c9rPsINUpPdgRoFJVwEYm
// spotify:track:4iV5W9uYEdYUVa79Axb7Rh

/*
Handles posting data
*/
class Posting extends Component{
	constructor(){
		super();
		this.state={
			user_id:'',
			playlist_id:'',
			uris:'',
			loggedIn: false,
		}

		// This binding is necessary to make `this` work in the callback
    this.handleClick = this.handleClick.bind(this);
	}


  componentDidMount(){
	 	if(localStorage.getItem('accessToken') == null || !localStorage.getItem('accessToken')){
	 	
	 		return;
	 	}
	 	else{
	 		this.setState({loggedIn: true})

	 		fetch('https://api.spotify.com/v1/me', {
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
      }).then(response => response.json())
      .then(data => this.setState({
      user_id: data.id
    }))
	 	}
	}

	handlePlaylist(event)
	{
		this.setState({
			playlist_id: event.target.value
		})
	}

	handleUri(event)
	{
		this.setState({
			uris: event.target.value
		})
	}

	handleClick(){
		var url = 'https://api.spotify.com/v1/users/' + this.state.user_id + '/playlists/' + this.state.playlist_id + '/tracks?uris=' + this.state.uris
		//console.log(url)

		fetch(url, {
			method: 'POST', // can be PUT
			headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
		}).then(response => response.json())
		.catch(error => alert(JSON.stringify(error)))
		.then(response => alert(JSON.stringify(response)))
	}

	render(){
		return(
			<div style={{width: "70%", display: 'inline-block', 'padding-top': '70px'}}>
			  {this.state.loggedIn ? 
			  <div>
			  <h1> Add songs to your playlists </h1>
			  <h5> Note: On success: return snapshot_id </h5>
			  <h2>{"\n"} </h2>
			  <input type="text" onChange={this.handlePlaylist.bind(this)}/> playlist_id
			  <h2>{"\n"} </h2>
			  <input type="text" onChange={this.handleUri.bind(this)}/> uris
			  <h2>{"\n"} </h2>
			  <button className="btn btn-outline-success my-2 my-sm-0" onClick={this.handleClick}> Submit </button>
	
			  
			  </div>
			  : <h1> Please login </h1>}
			</div>
	  );
	}
}

export default Posting