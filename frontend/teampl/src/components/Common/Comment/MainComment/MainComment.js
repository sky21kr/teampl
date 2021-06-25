import React from 'react';
import './MainComment.scss'

const MainComment  = ({ writer, date, content, toggleReplyInput }) => {

    return (
        <div className="mainComment"> 
            <div className="commentHeader">
                <div>
                    <span>{writer}</span>
                    <span>{date}</span>
                </div>
                <span onClick={toggleReplyInput}>대댓글</span>
            </div>
            <div className="commentContent">
                {content}
            </div>
        </div>
    )
}

export default MainComment;