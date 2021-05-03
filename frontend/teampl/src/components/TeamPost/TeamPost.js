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
            
        </div>
    )
}

export default TeamPost;