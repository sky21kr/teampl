import React from 'react';
import SubMenu from '@/components/SubMenu/SubMenu'
import TeamSearchMain from '@/components/TeamSearchMain/TeamSearchMain'

const TeamSearch = ({match, location}) => {
    return (
        <div>
            <SubMenu/>
            <TeamSearchMain
                cateCode={match.params.cateCode}
                search={location.search}
            />
        </div>
    )
}

export default TeamSearch;