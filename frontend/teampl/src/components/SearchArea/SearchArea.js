import React, { useState } from 'react';
import './SearchArea.scss'
import '..../images/search.svg'
import { useHistory } from 'react-router-dom';

function SearchArea() {
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

    return(
        <div className="searchWrap"> 
            <h2 className="searchTitle">팀플을 만들고 찾아보세요!</h2>
            <div className="searchBox">
                <input
                    className="search"
                    onChange={changeKeyword}
                    onKeyPress={handleKeyPress}
                    placeholder="원하는 검색어를 입력해보세요!"/>
                <button onClick={clickSearch} >
                    <img src="/images/searchicon.svg"/>
                </button>
            </div>
        </div>
    )
}

export default SearchArea;