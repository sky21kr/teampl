import React from 'react';
import Myinfo from './Myinfo';
import MadeTeam from './MadeTeam';
import JoinedTeam from './JoinedTeam';
import './MyTeam.scss'

function MyTeam(){
    return(
        <>
        <Myinfo></Myinfo>
        <MadeTeam></MadeTeam>
        <JoinedTeam></JoinedTeam>
        </>
    );
}

export default MyTeam;
