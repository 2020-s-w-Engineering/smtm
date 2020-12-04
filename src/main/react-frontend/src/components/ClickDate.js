import React from 'react';
import './css/ClickDate.css';
import { Link, Redirect } from 'react-router-dom';

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
        //console.log(this.state.allResponseData)
        return (
            <div id='scroll'>
            <div>
            <div id="line1"></div>
           
           
                <div id="date">
                <h2>{this.state.allResponseData.date}</h2>
                </div>
                <div id="headline">
                <h2>아침</h2>
                </div>
                <div id="midline">
                <div id="color1">
                <h2>Main A</h2>
                </div>
  
                {this.getMenus(this.state.allResponseData.breakfastMains.A)}
                
                <div id="color1">
                <h2>Main C</h2>
                </div>
                {this.getMenus(this.state.allResponseData.breakfastMains.C)}
            </div>
            </div>

            <div id="mid">
            <div id="headline">
                <h2>점심</h2>
                </div>
                <div id="midline">
                <div id="color1">
                <h2>Main A</h2>
                </div>
                {this.getMenus(this.state.allResponseData.lunchMains.A)}
                <div id="color1">
                <h2>Main C</h2>
                </div>
                {this.getMenus(this.state.allResponseData.lunchMains.C)}
            </div>
           </div>
            <div id="bt">
            <div id="headline">
                <h2>저녁</h2>
                </div>
                <div id="midline">
                <div id="color1">
                <h2>Main A</h2>
                </div>
                {this.getMenus(this.state.allResponseData.dinnerMains.A)}
                <div id="color1">
                <h2>Main C</h2>
                </div>
                {this.getMenus(this.state.allResponseData.dinnerMains.C)}
            </div>
            <div id="line2"></div>
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
            menulist.push(<ul key={data[i].id}>
                <Link class="blank" to={{
                    pathname : "/menu",
                    state : {
                        menuElement : data[i]
                    }
                }}
                >{data[i].korName}
                </Link></ul>)
            i=i+1;
        }

        return (
        <div id='MenuList'>
            <div id="kcal">
            <h5>[{this.state.main.calories}]</h5>
            </div>
            {menulist}
        </div>
        );
    }
}

export default ClickDate;