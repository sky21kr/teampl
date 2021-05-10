import React from 'react';
import './PostContent.scss'

const PostContent  = () => {

    return (
        <div className="postContent">
            <div className="postHeader">
                <div className="postTitle"><b>이번에 어디서 모이나요?</b></div>
                <div className="postEditBtn">
                    <span>수정</span>
                    <span>삭제</span>
                </div>
            </div>
            <div className="postInfo">
                <span>2021.03.21 13:24:25</span>
                <span>자바 개발자</span>
            </div>
            <span className="postText">
                이번에 집합금지 풀려서 만날 수 있지 않나요?
                어디에 몇시에 볼 지 얘기해봐요
                댓글 ㄱㄱㄱ
            </span>
            <div className="commentNumber">댓글 6개</div>
        </div>
    )
}

export default PostContent;