import React, { useState, useEffect } from 'react';
import './TeamRecruitment.scss'
import PostContent from './PostContent/PostContent'
import Comment from '@/components/Common/Comment/Comment'
import SubsApplyModal from './SubsApplyModal/SubsApplyModal';
import { customAxios } from '@/lib/customAxios';
import { withRouter } from 'react-router-dom';

const TeamRecruitment = ({teamId, history}) => {

    const [detailData, setDetailData] = useState( {
        category: null,
        comments: [],
        datetime: null,
        introduction: null,
        nickname: null,
        status: null,
        title: null,
    })

    const [ commentContent, setCommentContent ] = useState('')
    
    useEffect(async () => {
        const response = (await customAxios.get('/teamcontents', { params: { teamid: teamId } } )).data;
        setDetailData({
            ...response,
        })
        console.log('response', response)
    }, [])

    const [ showModal, setShowModal ] = useState(false)

    const openSubsApplyModal = () => {
        setShowModal(true)
    }

    const closeSubsApplyModal = () => {
        setShowModal(false)
    }

    const clickBack = () => {
        history.goBack()
    }

    const changeCommentContent = (e) => {
        setCommentContent(e.target.value)
    }

    const submitComment = async () => {
        const params = {
            comment: commentContent,
            teamid: teamId,
            userid: sessionStorage.getItem('userid')
        }

        const response = await customAxios.post('/create', params)
    }

    const renderComment = detailData?.comments?.map((comment) => <Comment commentData={comment} teamId={teamId} key={comment.comment_id}/>)

    return (
        <div className="TeamRecruitment">
            <div onClick={clickBack}>
                &lt; 이전으로
            </div>
            <PostContent
                detailData={detailData}
                teamId={teamId}
            />
            
            { renderComment }

            <textarea className="commentInput" placeholder="댓글을 입력하세요" onChange={changeCommentContent}/>
            <div className="addComment">
                <button className="t-button" onClick={submitComment}>등록</button>
            </div>
            <div className="submitJoin">
                <button
                    className="t-button"
                    onClick={openSubsApplyModal}>가입신청하기</button>
            </div>
            <SubsApplyModal
                teamName={"개발자 3명 구합니다"}
                showModal={showModal}
                teamId={teamId}
                closeModal={closeSubsApplyModal}
            />
        </div>
    )
}

export default withRouter(TeamRecruitment);