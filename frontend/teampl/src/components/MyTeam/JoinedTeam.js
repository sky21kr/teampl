import React from 'react';
import JoinedTeamComponent from './JoinedTeamComponent'
import Alarm from './Alarm';

function JoinedTeam(){
    return(
        <>
        <div className="titleWrap">
        <h2>내가 가입한 팀</h2>
        <Alarm></Alarm>
        </div>
        <div className="componentWrap">
        <JoinedTeamComponent></JoinedTeamComponent>
        <JoinedTeamComponent></JoinedTeamComponent>
        <JoinedTeamComponent></JoinedTeamComponent>
        </div>
        </>
    )
}

export default JoinedTeam;