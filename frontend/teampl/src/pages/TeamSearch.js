import React from 'react';
import SubMenu from '@/components/SubMenu/SubMenu'
import TeamSearchMain from '@/components/TeamSearchMain/TeamSearchMain'

const TeamSearch = () => {
    return (
        <div style={{width: '100%', height: '100%'}}>
            <SubMenu/>
            <TeamSearchMain/>
        </div>
    )
}

export default TeamSearch;