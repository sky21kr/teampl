import React, { Component, useState } from 'react';
import './ModifyTeam.scss'
import DefaultModal from '../Common/Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/createsuccess.svg';

function ModifyTeam(){


    const [ showModal, setShowModal ] = useState(false);

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }


        return(
            <>
            <div className="makeTeamWrap">
                <h3>팀 정보 수정하기</h3>
                <form onSubmit={(e)=>{
                    e.preventDefault();
                    console.log(e.target.teamName.value,e.target.teamSubject.value,e.target.numberOfMember.value,e.target.teamIntro.value)
                }}>
                    <input className="teamName" type="text" name="teamName" placeholder="팀 이름" required></input>
                    <select name="teamSubject">
                        <option disabled selected hidden>주제 분류</option>
                        <option>공부/학문</option>
                        <option>운동/스포츠</option>
                        <option>취미/오락</option>
                        <option>취업/취준</option>
                        <option>어학/자격증</option>
                        <option>IT/개발</option>
                        <option>기타</option>
                    </select>
                    <select name="numberOfMember">
                        <option disabled selected hidden>팀 인원</option>
                        <option>1명</option>
                        <option>2명</option>
                        <option>3명</option>
                        <option>4명</option>
                    </select>
                    <textarea className="teamIntro" name="teamIntro" placeholder="팀 소개글을 적어주세요!" required></textarea>
                    {/* form안의 내용이 작성,선택이 되어있을때만 openModal이 작동하게 if문을 추가해야됨 */}
                    <button className="basicBtn" onClick={openModal}>수정하기</button>

                </form>

            </div>

            <DefaultModal
                    showModal={showModal}
                    imgSrc={ImgSrc}
                    title="팀 정보가 수정되었습니다!"
                    btnOkText="팀 확인하러 가기"
                    closeModal={closeModal}
                    >
                </DefaultModal>
        
            </>
            )
    }
export default ModifyTeam;