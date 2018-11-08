import React, {Component} from 'react';
import './App.css';
var BarChart = require('react-d3-components').BarChart; // Bar library
var PieChart = require('react-d3-components').PieChart; // Pie library

/*
Handles following page
*/
class Following extends Component{
  /*
  Class' constructor
  */
  constructor(){
    super();
    this.state = {
      
      
      s: '',
      following: [] 
    }
  }

  /*
  Fetch api
  */
	componentDidMount(){
	 	if(localStorage.getItem('accessToken') == null || !localStorage.getItem('accessToken')){return;}
	 	else{
	 		console.log(localStorage.getItem('accessToken'))
	 		fetch('https://api.spotify.com/v1/me/following?type=artist', {
        headers: {'Authorization': 'Bearer ' + localStorage.getItem('accessToken')}
      }).then(response => response.json())
      .then((data) => 
      {
      	//console.log(data.artists.items)
      	this.setState({
      		following: data.artists.items,
      	})
      })
       
    }
	}

	render(){
	  console.log(this.state.following)
	  var sort = require('react-d3-components').ascending;
	  var BarChart = require('react-d3-components').BarChart;
	  let data = {
    		label: 'others',
    		values: [
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},

    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},

    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},

    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		{x:'others', y:0},
    		]
			}

		
		for (var i = 0; i < this.state.following.length; i++) {
			var check = 'false'
			console.log(check)
			console.log(this.state.following[i].genres[0])
			//console.log(this.state.following[i].genres[0].some(item => item.name == data.values[i].x))
      
      for(var j = 0; j < this.state.following.length; j++){
      	if(this.state.following[i].genres[0] == data.values[j].x){
      		data.values[j].y = data.values[j].y + 1
      		check = 'true'
      		break
      	}
      }

      if(check == 'false'){
      	data.values[i].x = this.state.following[i].genres[0]
      	data.values[i].y = 1
      }
    }
    console.log(data)

		return(
			
					
			<div >
			   
			  {this.state.following.length != 0 ?

			  <div style={{width: "50%", display: 'inline-block', 'padding-top': '70px'}}> 
			  <h1> My followings artists </h1>
			    <ol>

			     {this.state.following.map((item, i) =>
            <li>  {item.name} </li>  

           )}   
				  </ol>
				  <h1>  </h1>
				  <h1>  </h1>
				  <h2 style={{display: 'inline-block'}}> Your genre </h2>
 
				 
				 <BarChart data={data} width={3000} height={600} margin={{top: 10, bottom: 50, left: 50, right: 10}}/> 
				 <PieChart data={data} width={800} height={1000} margin={{top: 10, bottom: 10, left: 100, right: 100}} sort={sort}/>
				 </div>
				 : 
				 <div>
			 
				 	<h1> Oops, please login to your Spotify </h1>
				 </div>
				}
				 
			</div>
	  );
	}
}

export default Following
