import React from 'react';
import axios from 'axios';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
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
            // var base64img = 'data:image/png;base64,' +  this.state.imgData[0]; //base64 encoding 형식의 문자열로 바꿈
            // return <img className="imgsize" src={base64img} alt="" />
            const images = [];
            for (var i = 0; i < this.state.imgData.length; i++) {
                var base64img = 'data:image/png;base64,' +  this.state.imgData[i];
                images.push(<img src={base64img}></img>)
            }
            const settings = {
                dots: true,
                infinite: true,
                speed: 500,
                slidesToShow: 1,
                slidesToScroll: 1
            };

            return (
                <div>
                    <Slider {...settings} id="test">
                        {images}
                    </Slider>
                </div>
            )
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
            console.log("이미지 로딩 성공");
            console.log(res);
            menu_this.setState({
                imgData: res.data,
                flag: true
            })
        }).catch(function (err) {
            window.alert("이미지 로딩 실패");
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
            console.log("선호도 가져오기");
            console.log()
            preference_this.setState({
                preference: res.data,
            })
            var strPrefer = res.data.toString();
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
        var checked_val;
        for(var i = 0; i < 5; i++) {
            if (document.getElementById("prefer_" + (i + 1).toString()).checked === true) {
                checked_val = i + 1;
            }
        }

        var preference_this = this;
        api.post('/preference', null, {
            params: {
                id: preference_this.state.menuData["id"],
                preference: checked_val
            }
        })
        .then(function (res) {
            preference_this.setState({
                preference: checked_val
            })
            window.alert("선호도가 반영되었습니다.");
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
                <div id="radio1">
                1. <input id="prefer_1" type="radio" name="score" value="1" ></input>
                </div>
               <div id="radio2">
               2. <input id="prefer_2" type="radio" name="score" value="2" ></input> 
               </div> 
               <div id="radio3">
               3. <input id="prefer_3" type="radio" name="score" value="3" ></input> 
               </div>
               <div id="radio4">
               4. <input id="prefer_4" type="radio" name="score" value="4" ></input>
               </div>
                <div id="radio5"> 
                5. <input id="prefer_5" type="radio" name="score" value="5" ></input>
                </div>
                
            </div>
            <div>
            <button className="buttonMi2" onClick={this.upgradePreference.bind(this)}>선호도 저장</button>
            </div>
           
            {this.menuImg()}
          
            <div>
                <a href='/menuupload'><button className="buttonMi">사진 업로드</button></a>
            </div>
            </>
        );
    }
}

export default Menu