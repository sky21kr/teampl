import React from 'react';
import './TeamPost.scss'
import PostContent from './PostContent/PostContent'
import PostComment from './PostComment/PostComment'

const TeamPost = () => {

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
                <button className="t-button">가입신청하기</button>
            </div>
        </div>
    )
}

export default TeamPost;