import React from 'react';
import MadeTeamComponent from './MadeTeamComponent';
import Alarm from './Alarm';

function MadeTeam(){
    return(
        <>
        <div className="titleWrap">
        <h2>내가만든 팀</h2>
        <Alarm></Alarm>
        </div>
        <div className="componentWrap">
        <MadeTeamComponent></MadeTeamComponent>
        <MadeTeamComponent></MadeTeamComponent>
        <MadeTeamComponent></MadeTeamComponent>
        </div>
        </>
    )
}

export default MadeTeam;