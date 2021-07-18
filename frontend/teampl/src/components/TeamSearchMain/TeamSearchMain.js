import React, { useState, useEffect } from 'react';
import './TeamSearchMain.scss'
import TeamList from './TeamList/TeamList'
import { customAxios } from '@/lib/customAxios';
import { categoryCodeList } from '@/utils/CommonData/code';
import qs from 'qs'

const TeamSearchMain = ({cateCode, search}) => {
    const [teamList, setTeamList] = useState([]);
    const [searchKeyword, setSearchKeyword] = useState('');

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
              });

            setSearchKeyword(query.keyword)

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

    const getSearchCondition = () => {
        if(cateCode) return categoryCodeList.find((cate) => Number(cateCode) === cate.code)?.label
        else {
            return `'${searchKeyword}'로 검색한 검색 결과입니다.`
        }
    }

    return (
        <div className="teamSearchMain">
            <div>
                <div className="subjectTitle">팀 찾기 &gt; {getSearchCondition()}</div>
                <TeamList
                    teamList={teamList}
                />
                <div>페이지 네이션 있을 예정</div>
            </div>
        </div>
    )
}

export default TeamSearchMain;