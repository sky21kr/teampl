import React, { useState } from 'react';
import './SubMenu.scss'
import SubBar from './SubBar/SubBar'
import { useHistory } from 'react-router-dom';
import SearchIcon from '@/assets/images/searchicon.svg'

const SubMenu = () => {
    const history = useHistory()

    const [ keyword, setKeyword ] = useState('')

    const clickSearch = () => {
        history.push(`/team/search?keyword=${keyword}`)
    }

    const changeKeyword = (e) => {
        setKeyword(e.target.value)
    }

    const handleKeyPress = (e) => {
        if (e.key === "Enter") {
          clickSearch();
        }
      };

    return (
        <div className="subMenu">
            <SubBar />
            <div className="searchBar">
                <input onChange={changeKeyword} onKeyPress={handleKeyPress}/>
                <img src={SearchIcon} onClick={clickSearch}></img>
            </div>
        </div>
    )
}

export default SubMenu;