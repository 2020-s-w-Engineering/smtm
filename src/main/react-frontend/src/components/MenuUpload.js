import React from 'react';
import axios from 'axios';
import { Redirect } from 'react-router-dom';
import './css/MenuUpload.css';

class MenuUpload extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           isSetImg: false,
           isKorean: this.props.location.state.isKorean,
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
    
    watchingImg(e) {
        this.setState({
            isSetImg: true
        })
        var reader = new FileReader();
        
        reader.onload = function(e) {
            var img = document.createElement("img");
            img.setAttribute("src", e.target.result);
            img.setAttribute("width", "200");
            img.setAttribute("height", "200");
            document.querySelector("div#image_container").appendChild(img);
        };

        reader.readAsDataURL(e.target.files[0]);
    }

    selectImg() {
        if(this.state.isSetImg === false) {
            return(
                <img className ="imgsize" src="album.png" onClick={this.clickImg.bind(this)}></img>
            );
        }
        // } else {
        //     return (
        //         <h3>선택된 이미지</h3>
        //     );
        // }
    }

    render() {
        return(
            <form onSubmit={this.imgUpload.bind(this)}>
                <center>
                    <div id="header">
                        <h3>
                            {this.state.isKorean === true ? "업로드할 파일을 선택하세요." : "Choice Image"}
                        </h3>
                    </div>
                    <div>
                        <input type='file' name='file' id='file' onChange={this.watchingImg.bind(this)} required></input>
                        {/* <img className ="imgsize" src="album.png" onClick={this.clickImg.bind(this)}></img>
                        <div id="image_container"></div> */}
                        {this.selectImg()}
                        <div id="image_container"></div>
                        <center>
                        {this.state.isKorean === true ? <input type='submit' value="확인"></input> : <input type='submit' value="upload"></input>}
                        </center>
                    </div>
                </center>
            </form>
        );
    }
}

export default MenuUpload