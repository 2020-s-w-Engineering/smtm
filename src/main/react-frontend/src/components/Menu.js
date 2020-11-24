import React from 'react';
import axios from 'axios';

class Menu extends React.Component {
    constructor(props){
        super(props);
    }

    menuClick(e) {
        e.preventDefault();
        
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })

        api.get('/images', { params: {
            id: "수육국밥"
        }}).then(function (res) {
            console.log(res);
        }).catch(function (err) {
            console.log(err);
        })
    }

    render() {
        return(
            <button onClick={this.menuClick.bind(this)}>Menu</button>
        );
    }
}

export default Menu