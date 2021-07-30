import React from 'react';
import SubMenu from '@/components/SubMenu/SubMenu'
import TeamRecruitmentComponent from '@/components/TeamRecruitment/TeamRecruitment'

const TeamRecruitment = ({match}) => {
    return (
        <div>
            <SubMenu/>
            <TeamRecruitmentComponent
                teamId={match.params.teamId}
            />
        </div>
    )
}

export default TeamRecruitment;