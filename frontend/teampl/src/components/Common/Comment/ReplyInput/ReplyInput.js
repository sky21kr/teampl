import React from 'react';
import './ReplyInput.scss'

const ReplyComment  = ({ showReplyInput, submitReply }) => {
    return (
        
        <div className={ showReplyInput ? "replyInput" : 'hidden'}>
            <textarea placeholder="대댓글을 입력하세요"/>
            <button className="t-button" onClick={submitReply}>
                등록
            </button>
        </div>
    )
}

export default ReplyComment;