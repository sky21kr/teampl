import React, { useEffect } from 'react';
import './RecruitComponent.scss';
import RecruitBox from '@/components/Common/RecruitBox/RecruitBox';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

function RecruitComponent({ teamList }){
    const renderTeamList = teamList.map((teamData) => <RecruitBox key={teamData.teamid} teamData={teamData}/>)

    return(
        <div className="recruitBoxWrap">
            {renderTeamList}
        </div>
    );
}

export default RecruitComponent;