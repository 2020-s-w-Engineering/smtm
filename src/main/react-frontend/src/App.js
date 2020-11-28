import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import './App.css'
import './components/css/Login.css';
import './components/css/componentCss.css';
import './components/css/MyPage.css';
import Login from './components/Login';
import Register from './components/Register';
import Calendar from './components/Calendar'
import MyPage from './components/MyPage';
import Home from './components/Home';
import ClickDate from './components/ClickDate';
import Menu from './components/Menu'; // Test 성복


class App extends React.Component{
  constructor(props){
    super(props);
    this.state={
      isLoggedIn : false,
      userInfo: null
    }
  }
  
  render(){
    console.log("App render");
    return (
      <div className="app">
        <h2>_______ mobile</h2>
        <div id='screen'>
          <BrowserRouter>
            <Switch>
              {this.state.isLoggedIn ? (
                <>
                <Route exact path="/">
                  <Home userInfo={this.state.userInfo} />
                </Route>

                <Route path="/menu">
                  <Calendar></Calendar>
                </Route>

                <Route path="/myPage">
                  <MyPage userInfo={this.state.userInfo} />
                </Route>

                <Route path="/clickDate">
                  <ClickDate/>
                </Route>
                </>
              ) : (
                <>
                <Route path='/menu'>
                  <Menu/>
                </Route>
                
                <Route exact path="/">
                  <Home userInfo={this.state.userInfo}/>
                </Route>

                <Route path="/Login">
                  <Login onSubmit={
                    function(_isLoggedIn, userInfo){
                      this.setState({
                        isLoggedIn:_isLoggedIn,
                        userInfo: userInfo,
                      })
                  }.bind(this)}/>
                </Route>
  
                <Route path="/Register">
                  <Register onSubmit={
                      function(_isLoggedIn, userInfo){
                        this.setState({
                          isLoggedIn:_isLoggedIn,
                          userInfo: userInfo,
                        })
                    }.bind(this)}/>
                </Route>
                </>
              )}
            </Switch>
          </BrowserRouter>
        </div>
      </div>
    );
  }
}

export default App;