import React, { Component } from 'react';
import { Route } from 'react-router-dom'
import './App.css';
import { Home, SignUp } from './pages'

class App extends Component {
  render() {
    return (
      <div>
        <div>헤더</div>
        <Route exact path='/' component={Home}/>
        <Route path='/sign-up' component={SignUp}/>
        <div>푸터</div>
      </div>
    )
  }
}

export default App;
