import React from 'react';
import axios from 'axios';
import default_photo from '../images/default_photo.png';
import './css/MenuImage.css';

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

    menuImg() {
        if (this.state.imgData !== "") {
            var base64img = 'data:image/png;base64,' +  this.state.imgData; //base64 encoding 형식의 문자열로 바꿈
            console.log(base64img);
            console.log(typeof base64img);
            return <img src={base64img} alt="" />
        } else {
            return <img src={default_photo} alt="" />
        }
    }

    render() {
        return(
            <>
            <h1>Menu Image</h1>
            <div id="input">
                1. <input type="radio" name="score1" value="true"></input> 
                2. <input type="radio" name="score2" value ="false"></input> 
                3. <input type="radio" name="score3"></input> 
                4. <input type="radio" name="score4"></input>
                5. <input type="radio" name="score5"></input>
            </div>
            <div>
            <button class="buttonMi2">선호도 저장</button>
            </div>
           
           <img class="imgsize" src={this.menuImg()}></img>
          
        
            <div>
                <button class="buttonMi" onClick={this.menuClick.bind(this)}>사진 업로드</button>
                </div>
                
          
            
            </>
        );
    }
}

export default Menu