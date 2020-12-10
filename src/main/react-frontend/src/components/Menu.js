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
            preference: 0,
        }
        this.setPreference()
    }

    menuImg() {
        if (this.state.imgData !== "") {
            var base64img = 'data:image/png;base64,' +  this.state.imgData[0]; //base64 encoding 형식의 문자열로 바꿈
            return <img className="imgsize" src={base64img} alt="" />
        } else {
            return <img className="imgsize" src={default_photo} alt="" />
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

    setPreference() {
        const api = axios.create({
            baseURL: 'http://localhost:8080/users'
        })
        // get 방식을 이용해서 test할 이미지를 가져옴
        var preference_this = this;
        api.get('/preference/' + preference_this.state.menuData["id"])
        .then(function (res) {
            console.log(res);
            preference_this.setState({
                preference: res.data,
            })
            var test_prefer = 1;
            var strPrefer = test_prefer.toString();
            document.getElementById("prefer_" + strPrefer).checked = true;
        }).catch(function (err) {
            console.log(err);
        })
    }

    upgradePreference() {
        const api = axios.create({
            baseURL: 'http://localhost:8080/users'
        })
        // post 방식을 이용해서 선호도를 업데이트함
        var preference_this = this;
        api.post('/preference', null, {
            // github readme에 메뉴 db id로 쓰여있어서 12월10에 다시 물어볼꺼임 (성복)
        })
        .then(function (res) {
            console.log(res);
            preference_this.setState({
                preference: res.data,
            })
            var test_prefer = 1;
            var strPrefer = test_prefer.toString();
            document.getElementById("prefer_" + strPrefer).checked = true;
        }).catch(function (err) {
            console.log(err);
        })
    }

    handleRadio(e) {
        e.preventDefault();
        this.setState({
            preference: e.target.value
        });
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
                1. <input id="prefer_1" type="radio" name="score" value="1" onChange={this.handleRadio.bind(this)}></input> 
                2. <input id="prefer_2" type="radio" name="score" value="2" onChange={this.handleRadio.bind(this)}></input> 
                3. <input id="prefer_3" type="radio" name="score" value="3" onChange={this.handleRadio.bind(this)}></input> 
                4. <input id="prefer_4" type="radio" name="score" value="4" onChange={this.handleRadio.bind(this)}></input>
                5. <input id="prefer_5" type="radio" name="score" value="5" onChange={this.handleRadio.bind(this)}></input>
            </div>
            <div>
            <button className="buttonMi2" onClick={this.upgradePreference.bind(this)}>선호도 저장</button>
            </div>
           
            {this.menuImg()}
          
            <div>
                <button className="buttonMi">사진 업로드</button>
            </div>
            </>
        );
    }
}

export default Menu