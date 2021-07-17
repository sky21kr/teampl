import React, { useState, useEffect } from 'react';
import './TeamSearchMain.scss'
import TeamList from './TeamList/TeamList'
import { customAxios } from '@/lib/customAxios';
import { categoryCodeList } from '@/utils/CommonData/code';
import qs from 'qs'

const TeamSearchMain = ({cateCode, search}) => {
    const [teamList, setTeamList] = useState([]);


    useEffect(() => {
        fetchTeamList();
    }, [cateCode])

    const fetchTeamList = async () => {
        let URL = '/team'
        let searchParams = {
            category: 0,
            keyword: '',
            pageNumber: 0,
            pageSize: 9,
        }

        if(cateCode) {
            if(Number(cateCode) === 0) {
                searchParams = {
                    pageNumber: 0,
                    pageSize: 9,
                }    
            } else {
                searchParams = {
                    category: cateCode,
                    pageNumber: 0,
                    pageSize: 9,
                }
    
                URL += '/category'
            }
        } else if(search) {
            const query = qs.parse(search, {
                ignoreQueryPrefix: true
                // 문자열 맨 앞의 ?를 생력
              });

            searchParams = {
                keyword: query.keyword,
                pageNumber: 0,
                pageSize: 9,
            }

            URL += '/search'
        }

        const response = (await customAxios.get(URL, { params: searchParams } )).data.content;
        setTeamList(response);
    }

    const getCategoryName = (cateCode) => {
        console.log(cateCode, categoryCodeList)
        return categoryCodeList.find((cate) => Number(cateCode) === cate.code)?.label
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