import React, { Component } from 'react';
import { Route } from 'react-router-dom'
import './App.css';
import { Home, SignUp } from './pages'
import Header from './components/Header/Header'

class App extends Component {
  render() {
    return (
      <div>
        <Header/>
        <Route exact path='/' component={Home}/>
        <Route path='/sign-up' component={SignUp}/> {/*4*/}
        <Route path='/log-in' component={SignUp}/> {/*3*/}
        <Route path='/main' component={SignUp}/> {/*2, 5*/}

        <Route path='/team/make' component={SignUp}/> {/*6*/} 
        <Route path='/team/modify' component={SignUp}/> {/*6*/}
        <Route path='/team/search' component={SignUp}/> {/*7, 12*/}
        <Route path='/team/post' component={SignUp}/> {/*8*/}
        <Route path='/team' component={SignUp}/> {/*9*/}

        <Route path='/team/detail' component={SignUp}/> {/*10*/}
        <Route path='/team/detail/post' component={SignUp}/> {/*11*/}
        <div>푸터</div>
      </div>
    )
  }
}

export default App;
