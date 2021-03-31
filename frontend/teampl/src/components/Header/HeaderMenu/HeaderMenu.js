import React from 'react';
import './HeaderMenu.scss'
import { useHistory } from "react-router-dom";

const HeaderMenu = ({}) => {
    let history = useHistory();

    return (
        <div className="headerMenu">
            <div onClick={() => history.push('/team/make')}>팀 만들기</div>
            <div onClick={() => history.push('/team/search')}>팀 찾기</div>
        </div>
    )
}

export default HeaderMenu;