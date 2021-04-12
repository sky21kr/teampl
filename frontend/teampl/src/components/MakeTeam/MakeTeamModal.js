import React from 'react';
import './MakeTeam.scss'

const MakeTeamModal= (props)=>{
    //열기,닫기,모달 헤더 텍스트를 부모로부터 받아옴
    const {open,close,header}=props;

    return(
        <div className={open ? 'openModal modal':'closeModal'} >
            { open? (
                <>
                <div className="modalBg" onClick={close}></div>
                    <div className="makeTeamModal">
                    <img src="/images/createsucess.svg"></img>
                    <img className="closeBtn" onClick={close} src="/images/close.svg"></img>
                    <h3>짝짝짝! 당신의 팀이 만들어졌어요!</h3>
                    <p>이제 다른 멤버들의 가입 신청을 기다려주세요!</p>
                    <button className="confirmBtn" onClick={close}>팀 확인하러 가기</button>
                </div>
                </>
                ):null}

        </div>
    )
}

export default MakeTeamModal;