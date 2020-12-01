import React from 'react';
import axios from 'axios';
import './css/MenuUpload.css';
class MenuUpload extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           
        };
    }

    imgUpload(e) {
        e.preventDefault();
        
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        var menuUpload_this = this;
        api.post('/login', null, { params: {
            // parameters
            
        }}).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        });
    }

    render() {
        return(
            <form onSubmit={this.imgUpload.bind(this)}>
                <center>
                    <div id="header">
                    <h3>업로드할 파일을 선택하세요.</h3>
                    </div>
                
                <img id ="imgsize" src="album.png"></img>
                </center>

               <div> 
               <input type='file' required></input>
               </div>
                
                
            
            </form>
        );
    }
}

export default MenuUpload