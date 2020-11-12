import React from 'react';
import axios from 'axios';

class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username : "",
            password : "",
            language : ""
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
        api.post('/register', {
            userId : this.state.username,
            password : this.state.password,
            isKorean : this.state.language
        }).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        });
    }

    render() {
        return(
            <div>
                <h1>Register</h1>
                <form onSubmit={this.registerClick.bind(this)}>
                    <div>
                        <h5>Username</h5>
                        <input type='text' name='username' value={this.state.username} placeholder='username' required onChange={this.infoChange.bind(this)} />
                    </div>
                    <div>
                        <h5>Password</h5>
                        <input type='password' name='password' value={this.state.password} placeholder='password' required onChange={this.infoChange.bind(this)} />
                    </div>
                    <div>
                        <h5>Language</h5>
                        <input type='radio' name='language' value='true' onChange={this.infoChange.bind(this)} />Korean
                        <input type='radio' name='language' value='false' onChange={this.infoChange.bind(this)} />English
                    </div>
                    <input type='submit' value='Register' />
                </form>
            </div>
        );
    }
}

export default Register