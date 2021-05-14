import React, { Component, useState } from 'react';
import './MemberModal.scss'
import teammaster from '@/assets/images/teammaster.svg';
import teammember from '@/assets/images/teammember.svg';
import CloseBtn from '@/assets/images/close.svg'
import DefaultModal from './DefaultModal';
import ApplicationModal from './ApplicationModal';

const MemberModal = (props) => {

    MemberModal.defaultProps={
       username:"내이름",
        name:"유저이름",
        application:"가입신청글몇글자까지나오는지테스트하는중가나다"
    }

    const [ showModal, setShowModal ] = useState(false);

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }
    
    return (

        <div className={ props.showModal ? "defaultModalBackground" : "hidden"}>
            <div className="memberModal">
                <div className="title">
                멤버 관리
                </div>
                <img className="closeBtn-a" onClick={props.closeModal} src={CloseBtn}/>

                <div className="memberList">
                    <h6>가입한 팀원</h6>
                    <ul>
                        <li><span><img src={teammaster}/>{props.username}(나)</span> </li>
                        {props.member ? <li><span> <img src={teammember}/> {props.name}</span> <button className="kickOut">내보내기</button></li> : ''}
                        {props.member ? <li><span> <img src={teammember}/> {props.name}</span> <button className="kickOut">내보내기</button></li> : ''}
                        {props.member ? <li><span> <img src={teammember}/> {props.name}</span> <button className="kickOut">내보내기</button></li> : ''}
                      
                    </ul>
                </div>

                <div className="memberList">
                <h6>가입승인 대기중</h6>
                    <ul>
                    {props.candidate ? <li> <span><img src={teammember}/> {props.name}</span> <span className="application" onClick={openModal}> {props.application}</span> <span><button className="approve">승인</button> <button className="reject">거절</button></span></li> : <p className="no-candidate">가입신청을 한 사람이 없습니다.</p>}
                    <li> <span><img src={teammember}/> {props.name}</span> <span className="application" onClick={openModal}> {props.application}</span> <span><button className="approve">승인</button> <button className="reject">거절</button></span></li>
                    </ul>
                </div>

                <ApplicationModal
                showModal={showModal}
                closeModal={closeModal}
                application={"안녕하세요 지금 독학 5개월차 주니어입니다~ 혼자 공부하다가 막막해서 같이 스터디 하고 싶어서 신청합니다! 제 깃허브 계정 abcd로 들어가시면 코드 보실 수 있습니다"}
                ></ApplicationModal>

            
            </div>
        </div>
    )
}

export default MemberModal;