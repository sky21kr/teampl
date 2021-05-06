import React, { Component, useState } from 'react';
import './JoinedTeamMain.scss'
import messagecircle from '@/assets/images/message-circle.svg'

function Posting(props){

    const title = props.title
    const writer = props.writer
    const date = props.date
    const time = props.time
    const content = props.content
    const comments = props.comments

    Posting.defaultProps={
        title:"글제목",
        writer:"작성자",
        date : "작성일",
        time : "시간",
        content : "내용",
        comments : "댓글 수"
    }

    return(
    <div className="postingBox">
        <h3>{title}</h3>
        <div className="authorInfo">
            {writer} {date} {time}
        </div>
        
        <div className="contentArea">
        {content}
        </div>
        
        <div className="comments">
            <img src={messagecircle}/>{comments}
        </div>
        
        </div>

    )
}

export default Posting