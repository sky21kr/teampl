import React from 'react';
import TeamCard from '../_Common/TeamCard';
import Alarm from '../Alarm';
function MadeTeam({ myTeamList }) {
    const renderMyTeam = myTeamList.map((myTeam) => <TeamCard teamDetail={myTeam} key={myTeam.teamid}/>)

    return(
        <div>
            <div className="titleWrap">
                <h2>내가만든 팀</h2>
                <Alarm></Alarm>
            </div>
            <div className="componentWrap">
                {renderMyTeam}
            </div>
        </div>
    )
}

export default MadeTeam;