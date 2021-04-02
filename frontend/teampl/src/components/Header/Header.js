import React from 'react';
import './Header.scss'
import HeaderMenu from './HeaderMenu/HeaderMenu'
import MyMenu from './MyMenu/MyMenu'
import { useHistory } from "react-router-dom";

const Header = () => {
    let history = useHistory()
    return (
        <div className="header">
            <div className="headerLeft">
                <img className="logo" src="/images/logo.svg" onClick={() => history.push('/')}/>
                <HeaderMenu/>
            </div>
            <div className="headerRight">
                <MyMenu/>
            </div>  
        </div>
    )
}

export default Header;