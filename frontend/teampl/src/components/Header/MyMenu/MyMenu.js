import React from 'react';
import './MyMenu.scss'
import { withRouter } from 'react-router-dom';
import Cookies from 'js-cookie';


const MyMenu = ({ history }) => {

    const isLoggedIn = window.sessionStorage.getItem('token');
    
    const clickLogout = () => {
        window.sessionStorage.removeItem('userid');
        window.sessionStorage.removeItem('token');
        history.push('/')
    }

    const clickLogin = () => {
        history.push('/log-in')
    }


    return (
        <div className="MyMenu">
            { isLoggedIn ? <div onClick={clickLogout}>로그아웃</div>
            : <div onClick={clickLogin}>로그인</div>}
            <div onClick={() => history.push('/team')}>나의 팀</div>
        </div>
    )
}

export default withRouter (MyMenu);