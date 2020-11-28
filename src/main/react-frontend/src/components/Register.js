import React from 'react';
import axios from 'axios';
import { Redirect } from 'react-router-dom';
import './css/Login.css';


class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username : "",
            password : "",
            language : "",
            isLoggedIn : false,
        }
    }

    infoChange(e) {
        const {
            target: {name, value},
        } = e;
        if (name === 'username') {
            this.setState({username : value})
        } else if (name === 'password') {
            this.setState({password : value})
        } else if (name === 'language') {
            this.setState({language : value})
        }
    }

    registerClick(e) {
        // Register
        e.preventDefault();
        console.log(this.state.username);
        console.log(this.state.password);
        console.log(this.state.language);

        const api = axios.create({
            baseURL: 'http://localhost:8080/users'
        })
        var register_this = this;
        api.post('/register', null, { params: {
            userId : this.state.username,
            password : this.state.password,
            korean : this.state.language
        }}).then(function (response) {
            console.log(response);
            if (response.status === 200) {
                register_this.setState({isLoggedIn:true})
                register_this.props.onSubmit(
                    false, null
                );
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    render() {
        console.log("Register render");
        if(this.state.isLoggedIn === true){
            return <Redirect to='/'></Redirect>
        }
        return(
            <div>
                <div id="homeline">
                <h1>Register</h1>
                </div>
                
                <form onSubmit={this.registerClick.bind(this)}>
                    <div id="homeUsername">
                    <h5>Username</h5>
                    <p class = "underline">
                    <input type='text' name='username' value={this.state.username} placeholder='username' required onChange={this.infoChange.bind(this)} />
                    </p>  
                       
                        
                    </div>
                    <div id="homePassword">
                        <h5>Password</h5>
                        <input type='password' name='password' value={this.state.password} placeholder='password' required onChange={this.infoChange.bind(this)} />
                    </div>
                    <div id="Language">
                        
                        <div id = "text">
                        <h5>Language</h5>
                        </div>                       
                        
                        <div id="input">

                        <input type='radio' name='language' value='true' onChange={this.infoChange.bind(this)} />Korean
                        <input type='radio' name='language' value='false' onChange={this.infoChange.bind(this)} />English
                        </div>
                        </div>
                        
                    <center>
                    <button type='submit' class="button1">Register</button>
                    </center>
                    
                    </form>
            </div>
        );
    }
}

export default Register