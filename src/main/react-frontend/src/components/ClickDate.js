import React from 'react';
import './css/ClickDate.css';
import { Link} from 'react-router-dom';
import loadingGif from '../images/loading.gif';

class ClickDate extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            allResponseData : this.props.location.state.responseData,
            isKorean : this.props.location.state.isKorean,
            //isKorean : this.props.location.state.isKorean,
            loading : true
        };
    }
    componentDidMount(){
        var loading_this = this;
        setTimeout(function() {
            loading_this.setState({loading : false})
        }, 1000);
    }
    getMenus(_mainsAorC){
        var menu_this = this;
        return <MenuList main={_mainsAorC} isKorean={menu_this.state.isKorean}></MenuList>
    }
    render(){
        console.log(this.state.isKorean);
        if(this.state.loading === true) {
            return(
                <div>
                    <img id="loadingImg" alt="cannot show you" src={loadingGif}></img>
                </div>
            )
        }
        else{
        var morningStr = this.state.isKorean===true? "아침":"Morning";
        var lunchStr = this.state.isKorean===true? "점심":"Lunch";
        var dinnerStr = this.state.isKorean===true? "저녁":"Dinner";
        return (
            
            <div id='scroll'>
            <div>
            <div id="line1"></div>
           
                <div id="date">
                <h2>{this.state.allResponseData.date}</h2>
                </div>
                <div id="headline">
                <h2>{morningStr}</h2>
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
                <h2>{lunchStr}</h2>
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
                <h2>{dinnerStr}</h2>
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
}

class MenuList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            main : this.props.main,
            isKorean : this.props.isKorean
        };
    }
    render(){
        
        var menulist=[];
        var data = this.state.main.menus;

        var i=0;
        while(i<data.length){
            menulist.push(<ul key={data[i].id}>
                <a className="blank" href="/">{data[i].korName}</a></ul>)
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