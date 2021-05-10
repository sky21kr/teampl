import React from 'react';
import './SubComment.scss'

const SubComment  = (props) => {
    return (
        <div className="subComment">
            <div className="commentHeader">
                <div>
                    <span>{props.writer}</span>
                    <span>{props.date}</span>
                </div>
                <div>
                    <span>수정</span>
                    <span>삭제</span>
                </div>
            </div>
            <div className="commentContent">
                {props.content}
            </div>
        </div>
    )
}

export default SubComment;