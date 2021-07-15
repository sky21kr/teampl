import React, { Component, useState } from 'react';
import './MyInfo.scss'
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/askdelete.svg';

function Myinfo({ numberOfMyTeam, numberOfMemberTeam }){

    const [ showModal, setShowModal ] = useState(false);

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    const [ showModal2, setShowModal2 ] = useState(false);

    const openModal2 = () => {
        setShowModal2(true);
    }

    const closeModal2 = () => {
        setShowModal2(false);
    }

    Myinfo.defaultProps={
        userName : "유저이름",
        madeTeamNum : "N",
        joinedTeamNum : "N"
    }

    return(
        <>

                <DefaultModal
                    showModal={showModal}
                    imgSrc={ImgSrc}
                    title="정말로 팀플 계정을 삭제하시겠습니까?"
                    btnOkText="네, 삭제하겠습니다."
                    btnCancelText="아니오, 한번 더 생각해볼게요"
                    closeModal={closeModal}
                    >
                </DefaultModal>


                <DefaultModal
                    showModal={showModal2}
                    title="이름을 변경하시겠습니까?"
                    btnOkText="네"
                    btnCancelText="아니오"
                    closeModal={closeModal2}
                    >
                </DefaultModal>

{}
        <div className="myInfoWrap">
  
            <div>
                 <img src="./images/userimage.png"></img>

                 <div>
                 <h2>  <button onClick={openModal2}>이름변경</button></h2>
                 <p>내가만든 팀플 {numberOfMyTeam}개   |   내가 가입한 팀플 {numberOfMemberTeam}개</p>
                 </div>
            </div>

            <button onClick={openModal} >팀플 탈퇴하기</button>
        </div>
    
  
    </>
    )
}

export default Myinfo;
