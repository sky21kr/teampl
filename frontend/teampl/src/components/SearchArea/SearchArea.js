import React, { Component } from 'react';
import './SearchArea.scss'
import '..../images/search.svg'

class SearchArea extends Component{
    constructor(props){
        super(props)
    }

    render(){
        return(
            <div className="searchWrap">
            
            <h2 className="searchTitle">팀플을 만들고 찾아보세요!</h2>
            <div className="searchBox">
            <input type="text" className="search" placeholder="원하는 검색어를 입력해보세요!"></input><button><img src="/images/searchicon.svg"></img></button>
            </div>
            
            </div>
        )
    }
}

export default SearchArea;