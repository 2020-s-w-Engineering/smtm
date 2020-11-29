import React from 'react';
import axios from 'axios';

class MenuUpload extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           
        };
    }

    imgUpload(e) {
        e.preventDefault();
        
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        var menuUpload_this = this;
        api.post('/login', null, { params: {
            // parameters
            
        }}).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        });
    }

    render() {
        return(
            <form onSubmit={this.imgUpload.bind(this)}>
                <input type='file' required></input>
            </form>
        );
    }
}

export default MenuUpload