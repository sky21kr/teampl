import React from 'react';
import './TeamSearchMain.scss'
import TeamList from './TeamList/TeamList'

const TeamSearchMain = () => {

    return (
        <div className="teamSearchMain">
            <div>
                <div className="subjectTitle">팀 찾기 &gt; 전체</div>
                <TeamList/>
                <div>페이지 네이션 있을 예정</div>
            </div>
        </div>
    )
}

export default TeamSearchMain;