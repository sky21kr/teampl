import React, { Component, useState, useEffect } from 'react';
import './MemberModal.scss'
import teammaster from '@/assets/images/teammaster.svg';
import teammember from '@/assets/images/teammember.svg';
import CloseBtn from '@/assets/images/close.svg'
import ApplicationModal from '../../Common/Modal/DefaultModal/ApplicationModal';
import { customAxios } from '@/lib/customAxios';

const MemberModal = ({teamId, showModal, closeModal}) => {

    const userId = sessionStorage.getItem('userid')

    useEffect(() => {
        // async function fetchWaitMember() {
        //     console.log(await customAxios.get('/application', { params: { teamid: Number(teamId), userid: Number(userId) }}))
        // }
        // fetchWaitMember()

        async function fetchTeamMember() {
            setTeamMemberList((await customAxios.get('/teammember', { params: { teamid: Number(teamId) }})).data)
        }
        fetchTeamMember()
    }, [])

    MemberModal.defaultProps={
       username:"내이름",
        name:"유저이름",
        application:"가입신청글몇글자까지나오는지테스트하는중가나다"
    }

    const [ teamMemberList, setTeamMemberList ] = useState([]);
    const [ showApplicationModal, setShowApplicationModal ] = useState(false);

    // const openModal = () => {
    //     setShowApplicationModal(true);
    // }

    const closeApplicationModal = () => {
        setShowApplicationModal(false);
    }

    const renderTeamMemberList = teamMemberList.map((teamMember) =>
        <li>
            <span><img src={teammember}/>{teamMember.nickname}</span>
            {teamMember.userid !== Number(userId) ? <button className="kickOut">내보내기</button> : ''}
        </li>)
    
    return (

        <div className={ showModal ? "defaultModalBackground" : "hidden"}>
            <div className="memberModal">
                <div className="title">
                멤버 관리
                </div>
                <img className="closeBtn-a" onClick={closeModal} src={CloseBtn}/>

                <div className="memberList">
                    <h6>가입한 팀원</h6>
                    <ul>
                        {renderTeamMemberList}
                    </ul>
                </div>

                <div className="memberList">
                <h6>가입승인 대기중</h6>
                    <ul>
                    {/* {props.candidate ? <li> <span><img src={teammember}/> {props.name}</span> <span className="application" onClick={openModal}> {props.application}</span> <span><button className="approve">승인</button> <button className="reject">거절</button></span></li> : <p className="no-candidate">가입신청을 한 사람이 없습니다.</p>}
                    <li> <span><img src={teammember}/> {props.name}</span> <span className="application" onClick={openModal}> {props.application}</span> <span><button className="approve">승인</button> <button className="reject">거절</button></span></li> */}
                    </ul>
                </div>

                <ApplicationModal
                    showModal={showApplicationModal}
                    closeModal={closeApplicationModal}
                    application={"안녕하세요 지금 독학 5개월차 주니어입니다~ 혼자 공부하다가 막막해서 같이 스터디 하고 싶어서 신청합니다! 제 깃허브 계정 abcd로 들어가시면 코드 보실 수 있습니다"}
                ></ApplicationModal>

            
            </div>
        </div>
    )
}

export default MemberModal;