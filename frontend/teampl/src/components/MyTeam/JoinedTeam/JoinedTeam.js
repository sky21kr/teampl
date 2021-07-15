import React from 'react';
import TeamCard from '../_Common/TeamCard/TeamCard';
import Alarm from '../_Common/Alarm/Alarm';

function JoinedTeam({memberTeamList}){
    const renderMemberTeamList = memberTeamList.map((memberTeam) => <TeamCard teamDetail={memberTeam} key={memberTeam.teamid}/>)

    return(
        <div>
            <div className="titleWrap">
                <h2>내가 가입한 팀</h2>
                <Alarm></Alarm>
            </div>
            <div className="componentWrap">
                {renderMemberTeamList}
            </div>
        </div>
    )
}

export default JoinedTeam;