import React from 'react';
import axios from 'axios';
import './css/Calendar.css';
import './css/componentCss.css';
import CalendarAPI from 'react-calendar'
import { Redirect } from 'react-router-dom';
import colorChart from '../images/colorChart.JPG';

class Calendar extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isKorean : this.props.userInfo[1],
            month : "",
            day : "",
            fullDateFormat : "",
            pageChangeFlag : 0,
            responseData : ""
        };
    }
    
    getMonthValue(stringMonth){
        var monthValue = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        return monthValue.indexOf(stringMonth)+1
    }

    getDate(value, e){
        var valueToString = value.toString();
        var valueArray = valueToString.split(" ")
        var _fulldateformat = valueArray[3]+"-"+this.getMonthValue(valueArray[1])+"-"+valueArray[2]
        //alert(_fulldateformat)
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        var fullDateUrl='/'+_fulldateformat;
        var getdate_this=this;
        //console.log(fullDateUrl)
        api.get(fullDateUrl, null).then(function (response) {
            if (response.status === 200) {
                console.log(response.data)
                //console.log(response.data.breakfastMains);
                //console.log(response.data.breakfastMains.A);
                getdate_this.setState({
                    pageChangeFlag:1,
                    responseData:response.data
                })
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    render(){
        //console.log(this.state.isKorean);
        if(this.state.pageChangeFlag===1) {
            return <Redirect to={{
                pathname: '/clickDate',
                state : {
                    responseData : this.state.responseData,
                    isKorean : this.state.isKorean
                }
            }}></Redirect>
        }
        return (
        <div id='calendar'>
            <h1>calendar</h1>
            <CalendarAPI
            id='calendarAPI'
            onClickDay={this.getDate.bind(this)}
            locale="en-EN"
            />
            <ColorChart isKorean={this.state.isKorean}></ColorChart>
        </div>
        );
    }
}

class ColorChart extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isKorean : this.props.isKorean
        };
    }
    render(){
        return (
        <div id='colorChart'>
            {/*
            <label>{this.state.isKorean===true? '낮음' : "Low"}</label>
            <div id="lowColor"></div>
            <label>{this.state.isKorean===true? '중간' : 'Middle'}</label>
            <div id="midColor"></div>
            <label>{this.state.isKorean===true? '높음' : 'High'}</label>
            <div id="highColor"></div>
             */}
            <img id="colorChart" alt="cannot show you" src={colorChart}></img>
        </div>
        );
    }
}

export default Calendar;