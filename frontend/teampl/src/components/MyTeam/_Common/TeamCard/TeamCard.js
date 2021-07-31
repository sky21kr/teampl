import React from 'react';
import './TeamCard.scss'
import { Link } from 'react-router-dom';
import { categoryCodeList } from '@/utils/CommonData/code'


function MadeTeamComponent({teamDetail}){
    const getCategory = (category) => {
        return categoryCodeList.find((categoryCode) => categoryCode.code === category)
    }

    const setStatusStyle = (status) => {
        if(status) {
            return {
                color: '#ff3e3e',
                border: '1px solid #ff3e3e',
            }
        } else {
            return {
                color: '#c4c4c4',
                border: '1px solid #eeeeee',
            }
        }
    }

    return(
        <div className="myTeamBox">
            <div className="teamCardHeader">
                <div className="teamCategory" style={{background: `${getCategory(teamDetail.category).colorCode}`}}>{getCategory(teamDetail.category).label}</div>
                <div className="teamStatus" style={setStatusStyle(teamDetail.status)}>{teamDetail.status ? '모집중' : '모집완료'}</div>
            </div>
            <div>
                <h3>{teamDetail.title}</h3>
                <p>{teamDetail.introduction}</p>
            </div>
            <div>
                <Link to={`/team/${teamDetail.teamid}`}><button className="goToTeamBtn">팀 바로가기</button></Link>
            </div>
        </div>
    )
}

export default MadeTeamComponent;