import React from 'react';
import './css/componentCss.css';
import homeImg from '../images/mix_home.png';
import { Link } from 'react-router-dom';

class Home extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn : this.props.isLoggedIn,
            button1:this.props.button1,
            button2:this.props.button2
        };
    }

    getButton(){
        var _buttonComponent=null;
        if(this.state.isLoggedIn===false){
            _buttonComponent=<BeforeLogIn></BeforeLogIn>
        }
        else _buttonComponent=<AfterLogIn></AfterLogIn>
        return _buttonComponent;
    }

    render(){
        return (
        <div id='home'>
            <h1>SMTM</h1>
            <img id="homeImg" alt="cannot show you" src={homeImg}></img>
            {this.getButton()}
        </div>
        );
    }
}

class BeforeLogIn extends React.Component{
   render(){
        return (
        <div id="userInButton" align="right" >
            <div class="button" id='login'>
            <Link style={{ textDecoration: 'none' }} to='login'><h2>LogIn</h2></Link>
            </div>

            <div class="button" id='register'>
            <Link style={{ textDecoration: 'none' }} to='/register'><h2>Register</h2></Link>
            </div>            
        </div>
        );
    }
}

class AfterLogIn extends React.Component{
    render(){
         return (
         <div id="userInButton" align="right" >
             <div class="button" id='menu'>
             <Link style={{ textDecoration: 'none' }} to='/menu'><h2>Menu</h2></Link>
             </div>
 
             <div class="button" id='myPage'>
             <Link style={{ textDecoration: 'none' }} to='/myPage'><h2>My Page</h2></Link>
             </div>            
         </div>
         );
     }
 }
 


export default Home;