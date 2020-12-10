import React from 'react';
import './css/componentCss.css';
import './css/Home.css';
import homeImg from '../images/mix_home.png';
import { Link } from 'react-router-dom';

class Home extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userInfo : this.props.userInfo,
            button1:this.props.button1,
            button2:this.props.button2,
            isKorean : true
        };
    }

    static getDerivedStateFromProps(nextProps, nextState) {
        if(nextProps.userInfo != null){
        return {userInfo : nextProps.userInfo};
        }
    }
    
    getButton() {
        var _buttonComponent=null;
        if(this.state.userInfo === null){
            _buttonComponent=<BeforeLogIn></BeforeLogIn>
        }
        else _buttonComponent=<AfterLogIn></AfterLogIn>
        return _buttonComponent;
    }

    render(){
        console.log("Home render");
        console.log('Home');
        console.log(this.state.userInfo);
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
            <div>
            <div className="userInButton">
            <div className="button" id='login'>
            <Link style={{ textDecoration: 'none' }} to='login'><h2>LogIn</h2></Link>
            </div>
            
            <div className="button" id='register'>
            <Link style={{ textDecoration: 'none' }} to='/register'><h2>Register</h2></Link>
            </div> 
            </div>
               
            </div>            
        );
    }
}

class AfterLogIn extends React.Component{
    render(){
         return (
         <div className="userInButton"  >
             <div className="button" id='menu'>
             <Link style={{ textDecoration: 'none' }} to='/calendar'><h2>Menu</h2></Link>
             </div>
 
             <div className="button" id='myPage'>
             <Link style={{ textDecoration: 'none' }} to='/myPage'><h2>My Page</h2></Link>
             </div>           
         </div>
         );
     }
 }

export default Home;