import React from 'react';
import SubMenu from '@/components/SubMenu/SubMenu'
import TeamSearchMain from '@/components/TeamSearchMain/TeamSearchMain'

const TeamSearch = ({match}) => {
    return (
        <div>
            <SubMenu/>
            <TeamSearchMain
                cateCode={match.params.cateCode}
            />
        </div>
    )
}

export default TeamSearch;