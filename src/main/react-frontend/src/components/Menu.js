import React from 'react';
import axios from 'axios';
import default_photo from '../images/default_photo.png';
import './css/MenuImage.css';

class Menu extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            imgData: "",
            menuData : this.props.location.state.menuElement,
            flag: false,
        }
    }

    menuImg() {
        if (this.state.imgData !== "") {
            var base64img = 'data:image/png;base64,' +  this.state.imgData[0]; //base64 encoding 형식의 문자열로 바꿈
            return <img class="imgsize" src={base64img} alt="" />
        } else {
            return <img class="imgsize" src={default_photo} alt="" />
        }
    }

    imgload() {
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        // get 방식을 이용해서 test할 이미지를 가져옴
        var menu_this = this;
        api.get('/images', { params : {
            id: menu_this.state.menuData["id"]
        }})
        .then(function (res) {
            console.log(res);
            menu_this.setState({
                imgData: res.data,
                flag: true
            })
        }).catch(function (err) {
            console.log(err);
        })
    }

    render() {
        console.log(this.state.menuData)
        if(this.state.flag === false) {
            this.imgload()
        }
        return(
            <>
            <h1>Menu Image</h1>
            <div id="input">
                1. <input type="radio" name="score" value="1"></input> 
                2. <input type="radio" name="score" value="2"></input> 
                3. <input type="radio" name="score" value="3"></input> 
                4. <input type="radio" name="score" value="4"></input>
                5. <input type="radio" name="score" value="5"></input>
            </div>
            <div>
            <button class="buttonMi2">선호도 저장</button>
            </div>
           
            {this.menuImg()}
          
            <div>
                <button class="buttonMi">사진 업로드</button>
            </div>
            </>
        );
    }
}

export default Menu