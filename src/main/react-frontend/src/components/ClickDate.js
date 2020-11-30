import React from 'react';
import './css/componentCss.css';

class ClickDate extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            allResponseData : this.props.location.state.responseData
        };
    }
    getMenus(_mainsAorC){
        return <MenuList main={_mainsAorC}></MenuList>
    }
    render(){
        console.log(this.state.allResponseData)
        return (
        <div id='clickDate'>
            <div>
                <h2>{this.state.allResponseData.date}</h2>
                <h3>아침</h3>
                <hr color='black' size='4'></hr>
                <h4>Main A</h4>
                {this.getMenus(this.state.allResponseData.breakfastMains.A)}
                <hr></hr>
                <h4>Main C</h4>
                {this.getMenus(this.state.allResponseData.breakfastMains.C)}
            </div>

            <div>
                <h3>점심</h3>
                <hr color='black' size='4'></hr>
                <h4>Main A</h4>
                {this.getMenus(this.state.allResponseData.lunchMains.A)}
                <hr></hr>
                <h4>Main C</h4>
                {this.getMenus(this.state.allResponseData.lunchMains.C)}
            </div>

            <div>
                <h3>저녁</h3>
                <hr color='black' size='4'></hr>
                <h4>Main A</h4>
                {this.getMenus(this.state.allResponseData.dinnerMains.A)}
                <hr></hr>
                <h4>Main C</h4>
                {this.getMenus(this.state.allResponseData.dinnerMains.C)}
            </div>
        </div>
        );
    }
}
 
class MenuList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            main : this.props.main
        };
    }
    render(){
        var menulist=[];
        var data = this.state.main.menus;

        var i=0;
        while(i<data.length){
            menulist.push(<li key={data[i].id}>
                <a href="/">{data[i].korName}</a></li>)
            i=i+1;
        }

        return (
        <div id='MenuList'>
            <h5>{this.state.main.calories}</h5>
            {menulist}
        </div>
        );
    }
}

export default ClickDate;