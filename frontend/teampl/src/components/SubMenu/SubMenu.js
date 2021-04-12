import React from 'react';
import './SubMenu.scss'
import SubBar from './SubBar/SubBar'
import SearchIcon from '@/assets/images/searchicon.svg'

const SubMenu = () => {

    return (
        <div className="subMenu">
            <SubBar />
            <div className="searchBar">
                <input/>
                <img src={SearchIcon}></img>
            </div>
        </div>
    )
}

export default SubMenu;