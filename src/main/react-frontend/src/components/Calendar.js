import React from 'react';
import axios from 'axios';
import './css/Calendar.css';
import CalendarAPI from 'react-calendar'
import { Redirect } from 'react-router-dom';

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
                //console.log(response.data)
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
        </div>
        );
    }
}

export default Calendar;