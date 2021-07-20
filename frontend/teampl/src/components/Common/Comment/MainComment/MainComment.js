import React, { useState } from 'react';
import './MainComment.scss'
import { customAxios } from '@/lib/customAxios';
import DefaultModal from '../../Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/askdelete.svg';


const MainComment  = ({ commentId, writer, date, content, toggleReplyInput }) => {
    const [ showModal, setShowModal ] = useState(false)

    const handleModify = () => {

    }

    const clickCommentDelete = () => {
        setShowModal(true)
    }

    const handleDelete = () => {
        customAxios.delete('delete?comment_id=35', { data: {
            comment_id: commentId,
        }})
    }

    const closeModal = () => {
        setShowModal(false)
    }

    return (
        <div className="mainComment"> 
            <div className="commentHeader">
                <div className="commentInfo">
                    <span>{writer}</span>
                    <span>{date}</span>
                </div>
                <div className="commentFeature">
                    <span onClick={toggleReplyInput}>대댓글</span>
                    <span onClick={handleModify}>수정</span>
                    <span onClick={clickCommentDelete}>삭제</span>
                </div>
            </div>
            <div className="commentContent">
                {content}
            </div>
            <DefaultModal
                showModal={showModal}
                closeModal={closeModal}
                imgSrc={ImgSrc}
                title="댓글을 삭제하시겠습니까?"
                btnOkText="삭제"
                btnOk={handleDelete}
                btnCancelText="취소"
                btnCancel={closeModal}
            />
        </div>
    )
}

export default MainComment;