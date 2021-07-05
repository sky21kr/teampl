import React from 'react';
import './TeamList.scss'
import RecruitBox from '@/components/Common/RecruitBox/RecruitBox'
import { useHistory } from "react-router-dom";


const TeamList = ({teamList}) => {

    let history = useHistory();

    const clickRecruitBox = (teamid) => {
        history.push(`/team/recruitment/${teamid}`)
    }
    
    const renderRecruitBox = teamList.map((team) => <RecruitBox teamData={team} key={team.teamid} onClick={(team) => clickRecruitBox(team.teamid)}/>)

    return (
        <div className="teamList">
            { renderRecruitBox }
        </div>
    )
}

export default TeamList;