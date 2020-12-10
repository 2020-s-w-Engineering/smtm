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

        var frm = new FormData();
        var photoFile = document.getElementById("file");
        frm.append("file", photoFile.files[0]);
        console.log(photoFile.files);
        api.post('/images', frm, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        });
    }

    clickImg(e) {
        e.preventDefault();
        document.getElementById('file').click();
    }
    
    render() {
        return(
            <form onSubmit={this.imgUpload.bind(this)}>
                <center>
                    <div id="header">
                        <h3>업로드할 파일을 선택하세요.</h3>
                    </div>
                    <div>
                        <input type='file' name='file' id='file' required></input>
                        <img className ="imgsize" src="album.png" onClick={this.clickImg.bind(this)}></img>
                        <input type='submit'>확인</input>
                    </div>
                </center>
            </form>
        );
    }
}

export default MenuUpload