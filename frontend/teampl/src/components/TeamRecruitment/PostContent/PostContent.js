import React, { useState } from 'react';
import './PostContent.scss'
import * as dayjs from 'dayjs'
import { categoryCodeList } from '@/utils/CommonData/code';
import { customAxios } from '@/lib/customAxios';
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/askdelete.svg';

const PostContent  = ({ detailData, teamId }) => {
    const userId = Number(sessionStorage.getItem('userid'))
    const [ showModal, setShowModal ] = useState(false)

    const getCategoryName = (cateCode) => {
        return categoryCodeList.find((cate) => cateCode === cate.code)?.label
    }

    const clickDelete = () => {
        setShowModal(true)
    }

    const handleRemove = () => {
        customAxios.delete(`team?teamid=${teamId}&userid=${window.sessionStorage.getItem('userid')}`)
    }

    return (
        <div className="postContent">
            <div className="postHeader">
                <div className="studyType">{getCategoryName(detailData.category)}</div>
                { detailData.userid === userId ? <div className="postEditBtn">
                    <span>수정</span>
                    <span onClick={clickDelete}>삭제</span>
                </div> : ''}
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
            <DefaultModal
                showModal={showModal}
                closeModal={() => setShowModal(false)}
                imgSrc={ImgSrc}
                title="해당 게시물을 삭제하시겠습니까"
                btnOkText="삭제"
                btnOk={handleRemove}
                btnCancelText="취소"
            />
        </div>
    )
}

export default PostContent;