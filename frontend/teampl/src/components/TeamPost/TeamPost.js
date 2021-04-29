import React from 'react';
import './TeamPost.scss'
import PostContent from './PostContent/PostContent'

const TeamPost = () => {

    return (
        <div className="teamPost">
            <div>
                &lt; 이전으로
            </div>
            <PostContent/>
            <div>
                <div>
                    댓글
                </div>
                <div>
                    대댓글
                </div>
                <div>
                    대댓글
                </div>
            </div>
        </div>
    )
}

export default TeamPost;