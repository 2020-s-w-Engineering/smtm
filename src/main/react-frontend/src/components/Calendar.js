import React from 'react';
import './css/componentCss.css';
import CalendarAPI from 'react-calendar'
import 'react-calendar/dist/Calendar.css'

class Calendar extends React.Component{

    render(){
        return (
        <div id='calendar'>
            <h1>calendar</h1>
            <CalendarAPI
            />
        </div>
        );
    }
}


export default Calendar;