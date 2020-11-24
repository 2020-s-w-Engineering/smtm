import React from 'react';
import axios from 'axios';
import { Redirect } from 'react-router-dom';

class LogIn extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username : "",
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
            console.log(response);
            if (response.status === 200) {
                login_this.setState({isLoggedIn:true})
                login_this.props.onSubmit(
                    true, response.config.params
                );
            }
        }).catch(function (error) {
            console.log(error);
        });

    }

    render() {
        console.log("Login render");
        if(this.state.isLoggedIn === true){
            return <Redirect to='/'></Redirect>
        }
        return(
            <div>
                <h1>Log In</h1>
                <form onSubmit={this.logInClick.bind(this)}>
                    <div>
                        <h5>Username</h5>
                        <input type='text' name='username' value={this.state.username} placeholder='username' required onChange={this.infoChange.bind(this)} />
                    </div>
                    <div>
                        <h5>Password</h5>
                        <input type='password' name='password' value={this.state.password} placeholder='password' required onChange={this.infoChange.bind(this)} />
                    </div>
                    <input type='submit' value='Log In'/>
                </form>
            </div>
        );
    }
}

export default LogIn