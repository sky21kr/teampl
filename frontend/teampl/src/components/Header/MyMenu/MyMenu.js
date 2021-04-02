import React from 'react';
import './MyMenu.scss'
import { withRouter } from 'react-router-dom';

const MyMenu = ({ history }) => {
    return (
        <div className="MyMenu">
            <div onClick={() => history.push('/log-in')}>로그인</div>
            <div onClick={() => history.push('/team')}>나의 팀</div>
        </div>
    )
}

export default withRouter (MyMenu);