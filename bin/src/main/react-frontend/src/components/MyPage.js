import React from 'react';

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

    /* ModifyClick(e) {
        // Register
        e.preventDefault();
        console.log(this.state.language);
        const axios = require('axios').default;            
    } */

    render() {
        console.log(this.state.userInfo);
        return(
            <div> 
              <div className ="box">             
              </div>             
                    <div>
                        <center>
                        <h3>My Page</h3>
                            <img src="user.png" witdh = "100px" height="100px"></img>
                        <h4> {this.state.userInfo[0].userId}님</h4>
                        </center>
                    </div>
                    
                    <center>
                        <div>Language : {this.state.isKorean}</div>
                        <div><button>수정</button></div>
                        <div><h2><button>Logout</button></h2></div>
                    </center>          
            </div>
        );
    }
}

export default MyPage