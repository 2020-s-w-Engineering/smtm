import React from 'react';
import axios from 'axios';

class Menu extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            imgData: ""
        }
    }

    menuClick(e) {
        e.preventDefault();
        
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        // get 방식을 이용해서 test할 이미지를 가져옴
        var menu_this = this;
        api.get('/test').then(function (res) {
            console.log(res);
            menu_this.setState({
                imgData: res.data[0]
            })
        }).catch(function (err) {
            console.log(err);
        })
    }

    render() {
        if (this.state.imgData !== "") {
            var base64img = 'data:image/png;base64,' +  this.state.imgData; //base64 encoding 형식의 문자열로 바꿈
            console.log(base64img);
            console.log(typeof base64img);
        }
        return(
            <>
            <button onClick={this.menuClick.bind(this)}>Menu</button>
            <img src={base64img} alt=""/>
            </>
        );
    }
}

export default Menu