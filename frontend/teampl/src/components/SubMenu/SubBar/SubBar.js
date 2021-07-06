import React from 'react';
import './SubBar.scss'
import { categoryCodeList } from '@/utils/CommonData/code';
import { withRouter } from 'react-router-dom';

const SubBar = ({history}) => {
    const renderCategoryMenu = categoryCodeList.map((category) => <div onClick={() => history.push(`/team/search/${category.code}`)}>{category.label}</div>)
    return (
        <div className="subBar">
            {renderCategoryMenu}
        </div>
    )
}

export default withRouter(SubBar);