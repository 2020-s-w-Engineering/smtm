import React from 'react';
import axios from 'axios';
import './css/MyPage.css';

class MyPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           isKorean: "Korean",
           userInfo: this.props.userInfo
        };
    }

    static getDerivedStateFromProps(nextProps, nextState) {
        if(nextProps.userInfo[1] !== true){
        return {isKorean : "English"};
        }
    }

    changeLanguage(e) {
        e.preventDefault();
        const api = axios.create({
            baseURL: 'http://localhost:8080/users'
        })
        var language_this = this;
        var changeInfo = [this.state.userInfo[0], !this.state.userInfo[1]]

        api.post('/update', null, { params: {
            userId : this.state.userInfo[0].userId,
            password : this.state.userInfo[0].password,
            korean: !language_this.state.userInfo[1]
        }}).then(function (response) {
            if (response.status === 200) {
                console.log(response.data)
                language_this.setState({
                    userInfo: changeInfo        
                })
                window.alert("언어 설정이 바뀌었습니다.");
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    render() {
        console.log(this.state.userInfo);
        return(
            <div>              
                <div id = "home1">
                    <h1>My Page</h1> 
                </div>
                <div id = "user">
                    <img alt="user img" src="user.png" witdh = "100px" height="100px"></img>
                        <h4> {this.state.userInfo[0].userId}님</h4>
                </div>
           
                <div id="a" id = "Language">Language : {this.state.isKorean}</div>
                <div id = "button1" onClick={this.changeLanguage.bind(this)}>수정</div>
  
                <center>
                    <a href='/'><div id = "button2">Logout</div></a>
                </center>
            </div>
        );
    }
}

export default MyPage
