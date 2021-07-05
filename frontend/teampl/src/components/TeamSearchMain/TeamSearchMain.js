import React, { useState, useEffect } from 'react';
import './TeamSearchMain.scss'
import TeamList from './TeamList/TeamList'
import { customAxios } from '@/lib/customAxios';

const TeamSearchMain = () => {

    const [category, setCategory] = useState(0);
    const [teamList, setTeamList] = useState([]);

    useEffect(() => {
        fetchTeamList();
    }, [category])

    const fetchTeamList = async () => {
        const searchParams = {
            pageNumber: 0,
            pageSize: 9,
        }

        const response = (await customAxios.get('/team', { params: searchParams } )).data.content;
        setTeamList(response);
    }

    return (
        <div className="teamSearchMain">
            <div>
                <div className="subjectTitle">팀 찾기 &gt; 전체</div>
                <TeamList
                    teamList={teamList}
                />
                <div>페이지 네이션 있을 예정</div>
            </div>
        </div>
    )
}

export default TeamSearchMain;