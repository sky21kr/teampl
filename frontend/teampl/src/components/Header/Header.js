import React from 'react';
import './Header.scss'
import HeaderMenu from './HeaderMenu/HeaderMenu'
import MyMenu from './MyMenu/MyMenu'

const Header = () => {
    return (
        <div className="header">
            <div className="headerLeft">
                <img src="/images/logo.svg" />
                <HeaderMenu/>
            </div>
            <div className="headerRight">
                <MyMenu/>
            </div>  
        </div>
    )
}

export default Header;