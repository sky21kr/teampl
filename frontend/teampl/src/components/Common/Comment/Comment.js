import React, { useState } from 'react';
import './Comment.scss'
import MainComment from './MainComment/MainComment'
import SubComment from './SubComment/SubComment'
import ReplyInput from './ReplyInput/ReplyInput'
import dayjs from 'dayjs';
import { customAxios } from '@/lib/customAxios';

const Comment  = ({commentData, teamId}) => {
    const [showReplyInput, setShowReplyInput] = useState(false) 
    const [ replyContent, setReplyContent ] = useState('')

    const toggleReplyInput = () => {
        setShowReplyInput(!showReplyInput)
    }

    const submitReply = async () => {
        const params = {
            comment: replyContent,
            refCommentId: commentData.comment_id,
            teamid: teamId,
            userid: sessionStorage.getItem('userid')
        }

        const r = await customAxios.post('/create', params)

        setShowReplyInput(!showReplyInput)
    }

    const changeReplyInput = (e) => {
        setReplyContent(e.target.value)
    }

    // const renderSubComment = commentData.subComment.map((comment) => <SubComment writer={'테스트'}/>)

    return (
        <div className="Comment">
            <MainComment
                commentId={commentData.comment_id}
                writer={commentData.writer?.nickname}
                date={dayjs(commentData.datetime).format('YYYY-MM-DD hh:mm:ss')}
                content={commentData.comment}
                toggleReplyInput={toggleReplyInput}
                />
            {/* { renderSubComment } */}
            {/* <SubComment
                writer={"자바개발자"}
                date={"2021.03.24 15:25:31"}
                content={"네 가능합니다! 가입신청 해주시면 돼요!"}/> */}
            <ReplyInput
                showReplyInput={showReplyInput}
                onChange={changeReplyInput}
                submitReply={submitReply}
            />
        </div>
    )
}

export default Comment;