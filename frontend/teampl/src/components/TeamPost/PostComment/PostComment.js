import React from 'react';
import './PostComment.scss'

const PostComment  = () => {
    return (
        <div className="postComment">
            <div className="mainComment"> 
                <div className="commentHeader">
                    <span>취업성공</span>
                    <span>2021.03.24 15:23:34</span>
                </div>
                <div className="commentContent">
                    안녕하세요 혼자 독학한지 6개월 됐는데 저도 같이 할 수 있을까요?
                </div>
            </div>

            <div className="subComment">
                <div className="commentHeader">
                    <span>자바개발자</span>
                    <span>2021.03.24 15:25:31</span>
                </div>
                <div className="commentContent">
                    네 가능합니다! 가입신청 해주시면 돼요!
                </div>
            </div>

            <div className="subComment">
                <div className="commentHeader">
                    <span>자바개발자</span>
                    <span>2021.03.24 15:25:31</span>
                </div>
                <div className="commentContent">
                    네 가능합니다! 가입신청 해주시면 돼요!
                </div>
            </div>

            <div className="commentInput">
                <textarea placeholder="대댓글을 입력하세요"/>
                <button className="t-button">
                    등록
                </button>
            </div>

        </div>
    )
}

export default PostComment;