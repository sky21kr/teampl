import React from 'react';
import './PostContent.scss'

const PostContent  = () => {

    return (
        <div className="postContent">
            <div className="postHeader">
                <div className="studyType">IT/개발</div>
                <div className="postEditBtn">
                    <span>수정</span>
                    <span>삭제</span>
                </div>
            </div>
            <div className="postTitle">
                <span>개발자 구합니다</span>
                <span className="recruitmentStatus">모집중</span>
            </div>
            <div className="postInfo">
                <span>2021.03.21 13:24:25</span>
                <span>자바 개발자</span>
            </div>
            <div className="postText">
                안녕하세요~
                감사합니다
            </div>
        </div>
    )
}

export default PostContent;