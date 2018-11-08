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
          <li><NavLink exact className="nav-link" to="/">Playlist</NavLink></li>
          <li><NavLink className="nav-link"to="/login">Login</NavLink></li>
          <li><NavLink className="nav-link" to="/home">Home</NavLink></li>
          </ul>         
        </div>   
      </nav>
    <Route exact path ="/" component={Playlist}/>
    <Route path="/home" component={Home}/>
    <Route path="/login" component={Login}/>

  </div>
  </Router>, 
  document.getElementById('root'));
//registerServiceWorker();
