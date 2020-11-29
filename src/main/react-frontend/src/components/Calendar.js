import React from 'react';
import axios from 'axios';
import './css/componentCss.css';
import CalendarAPI from 'react-calendar'
import 'react-calendar/dist/Calendar.css'
import { Redirect } from 'react-router-dom';

class Calendar extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            month : "",
            day : "",
            fullDateFormat : ""
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
        alert(_fulldateformat)
        this.setState({
            fullDateFormat : _fulldateformat
        })
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        var fullDateUrl='/'+_fulldateformat;
        console.log(fullDateUrl)
        api.get(fullDateUrl, null).then(function (response) {
            console.log(response);
            if (response.status === 200) {
                console.log(response);
            }
        }).catch(function (error) {
            console.log(error);
        });
    }

    render(){
        if(this.state.fullDateFormat!=="") {
            return <Redirect to={{
                pathname: '/clickDate',
                state: { id: '123' }
            }}></Redirect>
        }
        return (
        <div id='calendar'>
            <h1>calendar</h1>
            <CalendarAPI
            onClickDay={this.getDate.bind(this)}
            />
        </div>
        );
    }
}

export default Calendar;