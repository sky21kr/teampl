import React from 'react';
import { Link } from 'react-router-dom';

function JoinedTeamComponent(){
    return(
        <div className="myTeamBox">
        <div>
            <span className="subjectBtn">취업/취준</span>
            <span className="teamStatus">모집중</span>
        </div>
        <div>
            <h3>공기업 같이 준비하실분</h3>
            <p>같이 준비하실분 3분정도 모셔요 댓글주세요</p>
        </div>
        <div>
            <Link to="/"><button className="goToTeamBtn">팀 바로가기</button></Link>
        </div>
    </div>
    )
}

export default JoinedTeamComponent;