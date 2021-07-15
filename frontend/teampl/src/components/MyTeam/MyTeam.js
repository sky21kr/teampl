import React, { useEffect, useState } from 'react';
import Myinfo from './MyInfo/Myinfo';
import MadeTeam from './MadeTeam/MadeTeam';
import JoinedTeam from './JoinedTeam/JoinedTeam';
import { customAxios } from '@/lib/customAxios'
import './MyTeam.scss'

function MyTeam(){
    const userId = sessionStorage.getItem('userid')

    const [ myTeamList, setMyTeamList ] = useState([])
    const [ memberTeamList, setMemberTeamList ] = useState([])

    useEffect(async () => {
        const myTeamResponse = (await customAxios.get('/myteam', { params: { userid: userId } } )).data;
        setMyTeamList(myTeamResponse)

        const memberTeamResponse = (await customAxios.get('/memberteam', { params: { userid: userId } } )).data;
        setMemberTeamList(memberTeamResponse)

    }, [])

    return(
        <div>
            <Myinfo
                numberOfMyTeam={myTeamList.length}
                numberOfMemberTeam={memberTeamList.length}
            />
            <MadeTeam
                myTeamList={myTeamList}
            />
            <JoinedTeam
                memberTeamList={memberTeamList}
            />
        </div>
    );
}

export default MyTeam;
