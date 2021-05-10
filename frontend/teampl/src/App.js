import React, { Component } from 'react';
import { Route } from 'react-router-dom'
import { Home, SignUp, LogIn, TeamSearch, TeamRecruitment } from './pages'
import Header from './components/Header/Header'
import Footer from './components/Footer/Footer'
import MakeTeam from './components/MakeTeam/MakeTeam';
import MyTeam from './components/MyTeam/MyTeam';
import JoinedTeamMain from './components/JoinedTeamMain/JoinedTeamMain';

import '@/assets/styles/index.scss'
import './App.scss'

class App extends Component {
  render() {
    return (
      <div>
        <Header/>
        <Route exact path='/' component={Home}/>
        <Route path='/log-in' component={LogIn}/> {/*3*/}
        <Route path='/sign-up' component={SignUp}/> {/*4*/}
        <Route path='/main' component={SignUp}/> {/*2, 5*/}

        <Route exact path='/team/make' component={MakeTeam}/> {/*6*/} 
        <Route exact path='/team/modify' component={SignUp}/> {/*6*/}
        <Route exact path='/team/search' component={TeamSearch}/> {/*7, 12*/}
        <Route exact path='/team/recruitment/:teamId' component={TeamRecruitment}/> {/*8*/}
        <Route exact path='/team' component={MyTeam}/> {/*9*/}

        <Route exact path='/team/detail' component={JoinedTeamMain}/> {/*10*/}
        <Route exact path='/team/detail/post/:postId' component={SignUp}/> {/*11*/}
        <Footer/>
      </div>
    )
  }
}

export default App;
