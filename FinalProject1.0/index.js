import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route, NavLink, hashHistory} from 'react-router-dom';
import {render} from 'react-dom';
import './index.css';
import App from './App';
import Home from './Home';
import Login from './Login';
import Playlist from './Playlist';
//import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(
	<Router>
		<div className="container">

			<nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a className="navbar-brand" href="#">Spoticool</a>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarCollapse">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item active"><NavLink to="/">Home</NavLink></li>
          <li><NavLink to="/login">Login</NavLink></li>
          <li className="nav-item"><NavLink to="/playlist">Playlist</NavLink></li>
        </ul>
         
      </div>
    
    </nav>
    <Route exact path ="/" component={Home}/>
    <Route path="/playlist" component={Playlist}/>
    <Route path="/login" component={Login}/>

    </div>
  </Router>, 
	document.getElementById('root'));
//registerServiceWorker();
