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
            userId: this.props.userInfo[0].userId,
            password: this.props.userInfo[0].password,
            isKorean : this.props.userInfo[1],
            month : "",
            day : "",
            fullDateFormat : "",
            pageChangeFlag : 0,
            responseData : "",
            loading : false
        };
    }
    componentDidMount(){
        var loading_this = this;
        setTimeout(function() {
            loading_this.setState({loading : true})
        }, 1000);
    }
    getPreference(){
        var pre_list;
        const api = axios.create({
            baseURL: 'http://localhost:8080/users'
        })
        api.get('/preference', { params:{
            userId : this.state.userId,
            password : this.state.password
        }}).then(function (response) {
            if (response.status === 200) {
                console.log(response.data)
                var abbr_list = document.getElementsByTagName('abbr');
                for(var i=0;i<abbr_list.length;i++){
                        if(abbr_list[i].innerHTML==='4'){
                            abbr_list[i].parentElement.style.backgroundColor='red';
                        }
                        else if(abbr_list[i].innerHTML==='5'){
                            abbr_list[i].parentElement.style.backgroundColor='yellow';
                        }
                        else if(abbr_list[i].innerHTML==='6'){
                            abbr_list[i].parentElement.style.backgroundColor='red';
                        }
                        else if(abbr_list[i].innerHTML==='7'){
                            abbr_list[i].parentElement.style.backgroundColor='blue';
                        }
                        else if(abbr_list[i].innerHTML==='8'){
                            abbr_list[i].parentElement.style.backgroundColor='yellow';
                        }
                        else if(abbr_list[i].innerHTML==='9'){
                            abbr_list[i].parentElement.style.backgroundColor='blue';
                        }
                        else if(abbr_list[i].innerHTML==='10'){
                            abbr_list[i].parentElement.style.backgroundColor='blue';
                        }
                    if(i===30) break;
                }
            }
        }).catch(function (error) {
            console.log(error);
        });
    }
    
    getMonthValue(stringMonth){
        var monthValue = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        return monthValue.indexOf(stringMonth)+1
    }

    getDate(value, e){
        var valueToString = value.toString();
        var valueArray = valueToString.split(" ")
        var _fulldateformat = valueArray[3]+"-"+this.getMonthValue(valueArray[1])+"-"+valueArray[2]
        var date = Number(valueArray[2])
        if(date<4 || date>10) {
            alert("데이터가 존재하지 않습니다.\n다른 날짜를 눌러주세요.")
            return;
        }
        const api = axios.create({
            baseURL: 'http://localhost:8080/menus'
        })
        var fullDateUrl='/'+_fulldateformat;
        var getdate_this=this;
        api.get(fullDateUrl, null).then(function (response) {
            if (response.status === 200) {
                console.log(response.data)
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
        if(this.state.loading === true) {
            {this.getPreference()}
        }
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
            <div id="calendarHead">
                <h1>Calendar</h1>
            </div>
            <CalendarAPI
            id='calendarAPI'
            onClickDay={this.getDate.bind(this)}
            locale="en-EN"
            activeStartDate={new Date(2020,10,1)}
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
            <img id="colorChart" alt="cannot show you" src={colorChart}></img>
        </div>
        );
    }
}

export default Calendar;