import React, { useState } from 'react';
import './TeamRecruitment.scss'
import PostContent from './PostContent/PostContent'
import Comment from '@/components/Common/Comment/Comment'
import SubsApplyModal from './SubsApplyModal/SubsApplyModal';

const TeamRecruitment = () => {

    const [ showModal, setShowModal ] = useState(false)

    const openSubsApplyModal = () => {
        setShowModal(true)
    }

    const closeSubsApplyModal = () => {
        setShowModal(false)
    }

    return (
        <div className="TeamRecruitment">
            <div>
                &lt; 이전으로
            </div>
            <PostContent/>
            <Comment/>

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

export default TeamRecruitment;