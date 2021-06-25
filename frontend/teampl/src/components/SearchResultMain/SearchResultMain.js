import React from 'react';
import SearchResult from './SearchResult/SearchResult';
import '@/components/TeamSearchMain/TeamSearchMain.scss';

const SearchResultMain = (props)=>{

    const searchKeyword = props.searchKeyword;
    const resultNumber = props.resultNumber;

    SearchResultMain.defaultProps={
        searchKeyword:"검색어",
        resultNumber:"검색 결과 갯수"
    }

    return(
        <div className="teamSearchMain">
            <div>
                <div className="titleBox">
                    <div className="subjectTitle">팀 찾기 &gt; 전체</div>
                    <div className="resultTitle">"{searchKeyword}" 검색 결과 {resultNumber}개의 팀플을 찾았습니다. </div>
                </div>
                <SearchResult/>
                <div>페이지네이션</div>
            </div>
        </div>
    )
}

export default SearchResultMain;