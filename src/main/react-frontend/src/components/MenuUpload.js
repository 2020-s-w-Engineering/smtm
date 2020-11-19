import React from 'react';
import './App.css'

class MenuImage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           isKorean: ""
        };
    }

    infoChange(e) {
        const {
            target: {name, value},
        } = e;
        if (name === 'score1') {
            this.setState({username : value})
        } else if (name === 'password') {
            this.setState({password : value})
        } else if (name === 'language') {
            this.setState({language : value})
        }


    }
 

     /* ModifyClick(e) {
        // Register
        e.preventDefault();
        console.log(this.state.language);
        const axios = require('axios').default;         
    }  */


    render() {
      
        return(

            <form>
 <div className = "box1">
                <div className="in">
               
                <center>
                <h3>Menu Image</h3>
                   <h6>
                        1. <input type="radio" name='score1' value="score1"></input> 
                        2. <input type="radio" name='score2' value="score2"></input> 
                        3. <input type="radio" name="score3" value="score 3"></input> 
                        4. <input type="radio" name="score4" value="score 4"></input>
                        5. <input type="radio" name="score5" value="score 5"></input>
                       
                    </h6>
                   
                    <div>
                        <button2>선호도 저장</button2>
                    </div>
                    <div>
                        <button1>사진 업로드</button1>
                    </div>

               
                      <div>
                    <img scr="arrow1.png"  width ="40" height ="40"></img>
                    <img src="menu1.png" width ="120" height ="120"></img>
                    <img scr="arrow2.png" width ="40" height ="40"></img>
                    </div>
                        
                   
                   
                    </center>  
                    
            </div>
            </div>  
            </form>
           
        );
    }
}

export default MenuImage