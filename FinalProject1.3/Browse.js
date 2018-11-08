import React, {Component} from 'react';
import './App.css'; 
//<iframe src="https://open.spotify.com/embed/track/000xQL6tZNLJzIrtIgxqSl" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

/*
Handles button for playing tracks
*/
class Player extends Component{
	constructor(){
		super();

		this.playerEmbed = this.playerEmbed.bind(this);
	}

	playerEmbed(){
		<iframe src="https://open.spotify.com/embed/track/000xQL6tZNLJzIrtIgxqSl" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
		console.log("Playing" + this.props.player.name + " - " + this.props.player.artists[0].name)
	}

  render(){
  	return(
  		<div>
  		  
  		  <button onClick={this.playerEmbed} className="btn btn-outline-success my-2 my-sm-0">{this.props.player.name} - {this.props.player.artists[0].name} </button>
  		  <h2> {"\n"} </h2>
  		  <h2> {"\n"} </h2>

  		</div>
  	);
  }
}


/*
Handles top100 tracks
*/
class Browse extends Component{

	constructor(){
		super();
		this.state={
			loggedIn: false,
			tracks: [],
		}

		this.playerEmbed = this.playerEmbed.bind(this);

  }
	componentDidMount(){
	 	if(localStorage.getItem('accessToken') == null || !localStorage.getItem('accessToken')){	 
	 		return;
	 	}
	 	else{
	 		this.setState({loggedIn: true})
	 		console.log(localStorage.getItem('accessToken'))
	 		fetch('https://api.spotify.com/v1/users/spotify/playlists/4hOKQuZbraPDIfaGbM3lKI/tracks', {
        headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
      }).then(response => response.json())
      .then((data) => 
      {
      	this.setState({
      		tracks: data.items,
      	})
      })
       
    }
	}

	playerEmbed(){
		return(
		<div>
		<iframe src="https://open.spotify.com/embed/track/000xQL6tZNLJzIrtIgxqSl" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
		</div>
		);
	}

	render(){
		return(
			<div style={{width: "100%", display: 'inline-block', 'padding-top': '70px'}}>

			  {this.state.loggedIn ?
			  	<div>
			  	  <h1> Top 100 tracks, Click to play </h1>

			  	  <ol>
			       {this.state.tracks.map((item, i) =>
              //<button onClick={this.playerEmbed} >{item.track.name} - {item.track.artists[0].name} </button>
              //var iframe.src = 'https://open.spotify.com/embed/track/' + {item.track.id}

              <Player player={item.track}/>              
             )}   
				    </ol>
				    <iframe src="https://open.spotify.com/embed/track/000xQL6tZNLJzIrtIgxqSl" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

			  
			  </div>
			  : <h1> Please login </h1>
			  } 
			</div>
	  );
	}
}

export default Browse