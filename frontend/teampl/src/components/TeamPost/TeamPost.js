import React, { useState } from 'react';
import './TeamPost.scss'
import PostContent from './PostContent/PostContent'
import PostComment from './PostComment/PostComment'
import SubsApplyModal from './SubsApplyModal/SubsApplyModal';

const TeamPost = () => {

    const [ showModal, setShowModal ] = useState(false)

    const openSubsApplyModal = () => {
        setShowModal(true)
    }

    const closeSubsApplyModal = () => {
        setShowModal(false)
    }

    return (
        <div className="teamPost">
            <div>
                &lt; 이전으로
            </div>
            <PostContent/>
            <PostComment/>

            <textarea className="commentInput" placeholder="댓글을 입력하세요"/>
            <div className="addComment">
                <button className="t-button">등록</button>
            </div>
            <div className="submitJoin">
                <button
                    className="t-button"
                    onClick={openSubsApplyModal}>가입신청하기</button>
            </div>
            <SubsApplyModal
                teamName={"개발자 3명 구합니다"}
                showModal={showModal}
                closeModal={closeSubsApplyModal}
            />
        </div>
    )
}

export default TeamPost;