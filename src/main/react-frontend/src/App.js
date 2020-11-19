import React from 'react';
import { BrowserRouter, Route, Link, Switch } from "react-router-dom";
import './App.css'
import './components/css/componentCss.css';
import Login from './components/Login';
import Register from './components/Register';
import Calendar from './components/Calendar'
import UserComponent from './components/UserComponent';
import MyPage from './components/MyPage';

import Home from './components/Home'

class App extends React.Component{
  constructor(props){
    super(props);
    this.state={
      isLoggedIn : false,
      userId : ""
    }
  }
  render(){
    return (
      <div className="app">
        <h1>{this.state.isLoggedIn}</h1>
        <h2>_______ mobile</h2>
        <div id='screen'>
          <BrowserRouter>
          <Switch>
              <Route exact path="/">
              <Home isLoggedIn={this.state.isLoggedIn}/>
              </Route>

              <Route path="/Login">
              <Login isLoggedIn={this.state.isLoggedIn} onSubmit={
                function(_isLoggedIn){
                  this.setState({
                    isLoggedIn:_isLoggedIn
                  })
              }.bind(this)}/>
              </Route>
  
              <Route path="/Register">
              <Register/>
              </Route>
  
              <Route path="/menu">
              <Calendar></Calendar>
              </Route>

              <Route path="/myPage">
              <MyPage/>
              </Route>
          </Switch>
          </BrowserRouter>
        </div>
      </div>
    );
  }
}

export default App;