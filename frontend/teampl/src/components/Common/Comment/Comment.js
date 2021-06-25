import React, { useState } from 'react';
import './Comment.scss'
import MainComment from './MainComment/MainComment'
import SubComment from './SubComment/SubComment'
import ReplyInput from './ReplyInput/ReplyInput'

const Comment  = () => {

    const [showReplyInput, setShowReplyInput] = useState(false) 

    const toggleReplyInput = () => {
        setShowReplyInput(!showReplyInput)
    }

    const submitReply = () => {
        setShowReplyInput(!showReplyInput)
    }

    return (
        <div className="Comment">
            <MainComment
                writer={"취업성공"}
                date={"2021.03.24 15:23:34"}
                content={"안녕하세요 혼자 독학한지 6개월 됐는데 저도 같이 할 수 있을까요?"}
                toggleReplyInput={toggleReplyInput}
                />
            <SubComment
                writer={"자바개발자"}
                date={"2021.03.24 15:25:31"}
                content={"네 가능합니다! 가입신청 해주시면 돼요!"}/>
            <ReplyInput
                showReplyInput={showReplyInput}
                submitReply={submitReply}
            />
        </div>
    )
}

export default Comment;