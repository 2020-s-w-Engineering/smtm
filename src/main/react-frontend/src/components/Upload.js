import React from 'react';

class Upload extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
           isKorean: ""
        };
    }

    render() {
        return(
            <div>
                <center>
                <h5>업로드할 파일을 선택하세요.</h5>
                <img src="album.png" width = "60" height ="70" ></img>
                </center>
              
            </div>
        );
    }
}

export default Upload