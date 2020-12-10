import React from 'react';
import axios from 'axios';
import { Redirect } from 'react-router-dom';
import './css/Login.css';

class LogIn extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username : "",
            password : "",
            password : "",
            isLoggedIn : false
        };
    }

    infoChange(e) {
        const {
            target: {name, value},
        } = e;
        if (name === 'username') {
            this.setState({username : value})
        } else if (name === 'password') {
            this.setState({password : value})
        }
    }

    logInClick(e) {
        // login
        e.preventDefault();
        console.log(this.state.username);
        console.log(this.state.password);

        const api = axios.create({
            baseURL: 'http://localhost:8080/users'
        })
        var login_this = this;
        api.post('/login', null, { params: {
            userId : this.state.username,
            password : this.state.password
        }}).then(function (response) {
            console.log(response.data);
            if (response.status === 200) {
                login_this.setState({isLoggedIn:true})
                login_this.props.onSubmit(
                    true, [response.config.params, response.data]
                );
            }
            var id = response.config.params.userId;
            window.alert("로그인 되었습니다. 반갑습니다 " +  id+"님");
        }).catch(function (error) {
            console.log(error);
            window.alert("아이디와 비밀번호를 확인해주세요.")
        });

    }

    render() {
        console.log("Login render");
        if(this.state.isLoggedIn === true){
            return <Redirect to='/'></Redirect>
        }
        return(
            <div>          
                <div id="homeline1">
                <h1>Login</h1>
                </div>
           

                <form onSubmit={this.logInClick.bind(this)}>
                <div id="homeUsername">
                        <h5>Username</h5>
                        <div id="boxstyle">
                        <input type="text" name='username' value={this.state.username} required onChange={this.infoChange.bind(this)} />
                        </div>  
                    </div>

                    <center>
                    <div id="topline1"></div>
                    </center>

                    <div id="homePassword">
                        <h5>Password</h5>
                        <div id="boxstyle">
                        <input type='password' name='password' value={this.state.password} required onChange={this.infoChange.bind(this)} />
                        </div>
                    <center>
                    <div id="underline1"></div>
                    </center>
  
                    </div>
                    <center>
                    <button type='submit' className="button2">Log In</button>
                    </center>
                   
                </form>
            </div>
        );
    }
}

export default LogIn