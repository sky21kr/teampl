import React from 'react';
import './TeamList.scss'
import RecruitBox from '@/components/Common/RecruitBox/RecruitBox'
import { useHistory } from "react-router-dom";


const TeamList = () => {

    let history = useHistory();

    const clickRecruitBox = () => {
        history.push('/team/recuritment')
    }

    return (
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

export default TeamList;