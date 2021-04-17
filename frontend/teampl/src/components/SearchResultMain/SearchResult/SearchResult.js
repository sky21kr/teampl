import React from 'react';
import '@/components/TeamSearchMain/TeamList/TeamList.scss'
import RecruitBox from '@/components/Common/RecruitBox/RecruitBox'
import { useHistory } from "react-router-dom";

const SearchResult = ()=>{

    let history = useHistory();

    const clickRecruitBox = () => {
        history.push('/team/post')
    }

    return(
             <div className="teamList">
            <div onClick={clickRecruitBox}>
                <RecruitBox/>
            </div>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
            <RecruitBox/>
        </div>
    )
}

export default SearchResult;