import React from 'react';

class MyPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           isKorean: ""
        };
    }

    /* ModifyClick(e) {
        // Register
        e.preventDefault();
        console.log(this.state.language);
        const axios = require('axios').default;            
    } */

    render() {
      
        return(
            <div>
              
              <div className ="box">
              
              </div>
               
                    <div>
                        <center>
                        <h3>My Page</h3>
                            <img src="user.png" witdh = "100px" height="100px"></img>
                        <h4> ___________님</h4>
                        </center>
                    </div>
                    

                    <center>
                        <div>Language : </div>
                        <div><button1>수정</button1></div>
                        <div><h2><button2>Logout</button2></h2></div>
                    </center>
                    
                    
            
            </div>
        );
    }
}

export default MyPage