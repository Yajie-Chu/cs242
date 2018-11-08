import React, {Component} from 'react';
import './Login.css'; 
class Login extends Component{
	state = {
    loggedIn:false
  }

  loginHandle = () =>{
    this.setState(prevState => ({
      loggedIn: !prevState.loggedIn}))
  }

	render(){
		return(
			
			
			  <div id="wrapper">
			    <button class="btn btn-lg btn-primary btn-block" type="submit" onClick={() => {
			    {this.loginHandle.bind(this)};
                {window.location = window.location.href.includes('localhost') 
                ? 'http://localhost:8888/login'
                : 'https://spoticool-backend.herokuapp.com/login'}
                
                }}

                style={{'align-items': 'center','justify-content': 'center', 'padding-top': '70px', 'font-size':'50px'}}> {this.state.loggedIn ? 'Logout' : 'Login'} </button>
              </div>
			
	  );
	}
}

export default Login