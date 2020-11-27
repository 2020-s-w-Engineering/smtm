import React from 'react';
import './css/MyPage.css'
class MyPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           isKorean: "Korean",
           userInfo: this.props.userInfo
        };
    }

    static getDerivedStateFromProps(nextProps, nextState) {
        if(nextProps.userInfo[1] != true){
        return {isKorean : "English"};
        }
    }

    render() {
        console.log(this.state.userInfo);
        return(
            <div>              
                    <div id = "home">
                        <h2>My Page</h2> 
                    </div>
                    <div id = "user">
                    <img src="user.png" witdh = "100px" height="100px"></img>
                        <h4> {this.state.userInfo[0].userId}님</h4>
                    </div>
                   
                        <div id = "Language">Language : {this.state.isKorean}</div>
                        <div id = "button1">수정</div>
                        
                        <center>
                        <div id = "button2">Logout</div>
                        </center>
                        
                             
            </div>
        );
    }
}

export default MyPage