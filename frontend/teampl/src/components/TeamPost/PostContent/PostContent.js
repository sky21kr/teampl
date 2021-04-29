import React from 'react';
import './PostContent.scss'

const PostContent  = () => {

    return (
        <div className="postContent">
            <div className="postHeader">
                <div class="studyType">IT/개발</div>
                <div className="postEditBtn">
                    <div>수정</div>
                    <div>삭제</div>
                </div>
            </div>
            <div className="postTitle">
                <div>개발자 구합니다</div>
                <div>모집중</div>
            </div>
            <div className="post">
                <div>2021.03.21</div>
                <div>13:24:25</div>
                <div>자바 개발자</div>
            </div>
            <div className="postText">
                안녕하세요~
                감사합니다
            </div>
        </div>
    )
}

export default PostContent;