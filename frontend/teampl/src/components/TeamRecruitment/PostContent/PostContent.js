import React from 'react';
import './PostContent.scss'
import * as dayjs from 'dayjs'
import { categoryCodeList } from '@/utils/CommonData/code';
import { customAxios } from '@/lib/customAxios';

const PostContent  = ({ detailData, teamId }) => {

    const getCategoryName = (cateCode) => {
        return categoryCodeList.find((cate) => cateCode === cate.code)?.label
    }

    const clickDelete = () => {
        // 쿼리 스트링 타입
        customAxios.delete(`team?teamid=${teamId}&userid=${window.sessionStorage.getItem('userid')}`)
    }

    return (
        <div className="postContent">
            <div className="postHeader">
                <div className="studyType">{getCategoryName(detailData.category)}</div>
                <div className="postEditBtn">
                    <span>수정</span>
                    <span onClick={clickDelete}>삭제</span>
                </div>
            </div>
            <div className="postTitle">
                <span>{detailData.title}</span>
                <span className="recruitmentStatus">{detailData.status ? '모집중' : '모집완료'}</span>
            </div>
            <div className="postInfo">
                <span>{dayjs(detailData.datetime).format('YYYY. MM. DD')}</span>
                <span>{detailData.nickname}</span>
            </div>
            <div className="postText">
                {detailData.introduction}
            </div>
        </div>
    )
}

export default PostContent;