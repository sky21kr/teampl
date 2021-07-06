import React, { useState, useEffect } from 'react';
import './TeamSearchMain.scss'
import TeamList from './TeamList/TeamList'
import { customAxios } from '@/lib/customAxios';
import { categoryCodeList } from '@/utils/CommonData/code';


const TeamSearchMain = ({cateCode}) => {
    const [teamList, setTeamList] = useState([]);

    useEffect(() => {
        fetchTeamList(cateCode);
    }, [cateCode])

    const fetchTeamList = async (cateCode) => {
        if(cateCode == 0) {
            const searchParams = {
                pageNumber: 0,
                pageSize: 9,
            }

            const response = (await customAxios.get('/team', { params: searchParams } )).data.content;
            setTeamList(response);
        } else {
            const searchParams = {
                category: cateCode,
                pageNumber: 0,
                pageSize: 9,
            }

            const response = (await customAxios.get('/team/category', { params: searchParams } )).data.content;
            setTeamList(response);
        }
    }

    const getCategoryName = (cateCode) => {
        console.log(cateCode, categoryCodeList)
        return categoryCodeList.find((cate) => cateCode == cate.code).label
    }

    return (
        <div className="teamSearchMain">
            <div>
                <div className="subjectTitle">팀 찾기 &gt; {getCategoryName(cateCode)}</div>
                <TeamList
                    teamList={teamList}
                />
                <div>페이지 네이션 있을 예정</div>
            </div>
        </div>
    )
}

export default TeamSearchMain;