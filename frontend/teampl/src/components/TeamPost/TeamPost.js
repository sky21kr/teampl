import React, { useState } from 'react';
import './TeamPost.scss'
import PostContent from './PostContent/PostContent'
import Comment from '@/components/Common/Comment/Comment'

const TeamPost = () => {


    return (
        <div className="TeamPost">
            <div>
                &lt; 이전으로
            </div>
            <PostContent/>
            <Comment/>

            <textarea className="commentInput" placeholder="댓글을 입력하세요"/>
            <div className="addComment">
                <button className="t-button">등록</button>
            </div>
        </div>
    )
}

export default TeamPost;