import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route, NavLink, Redirect} from 'react-router-dom';
import {render} from 'react-dom';
import App from './App';
import Home from './Home';
import Login from './Login';
import Pl from './Playlist';
//import Following from './Following';
import queryString from 'query-string'; 
//import registerServiceWorker from './registerServiceWorker';
class Index extends Component{ 

  /*
  Constructor, runs when rendered. 
  */
  constructor(props){

    super(props);
    let parsed = queryString.parse(window.location.search);
    let accessToken = parsed.access_token;
    if(!accessToken){      
    this.state = {loggedIn: false}
    localStorage.clear();
    }
    else{
      this.state = {loggedIn: true}
    }

    this.loginHandle = this.loginHandle.bind(this);
    
  }

  /*
  Function to handle login and logout
  If it's logged in, it will redirect user to their playlist
  Else go to main page 
  */
  loginHandle(){
     this.setState(prevState => ({
      loggedIn: !prevState.loggedIn
    }));

    if(!this.state.loggedIn){
      //console.log("loading spotify")
      (window.location = window.location.href.includes('localhost') ? 'http://localhost:8888/login' : 'https://spoticool-backend.herokuapp.com/login')
      
    }
    else{

      (window.location = window.location.href.includes('localhost') ? 'http://localhost:3000/' : 'https://spoticool.herokuapp.com/')

      
    }
  }
  render(){
    return(

      /*
      All the navigation bar code
      */
      <Router>
    <div className="container">
      <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a className="navbar-brand" href="#">Spoticool</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarCollapse">
          <ul className="navbar-nav mr-auto">
          <li><NavLink exact className="nav-link" to="/">Home</NavLink></li>
          <li><NavLink className="nav-link" to="/playlist">Playlist</NavLink></li>
          
          </ul>         
          <form className="form-inline mt-2 mt-md-0">
          <button className="btn btn-outline-success my-2 my-sm-0" onClick={this.loginHandle}><NavLink className= 'nav-link'  to={this.state.loggedIn ? '#' : '#' }>{this.state.loggedIn ? 'Logout' : 'Login' }</NavLink></button>
          
        </form>
        </div>   
      </nav>
    <Route exact path ="/" component={Home}/>
    <Route path="/playlist" component={Pl}/>
    
  </div>
  </Router>
    );
  }
}

// React render function call
ReactDOM.render(
  <Index />, 
  document.getElementById('root'));


export default Index
//registerServiceWorker();
