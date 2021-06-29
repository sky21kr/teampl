import React, { Component, useState } from 'react';
import './MakeTeam.scss'
import DefaultModal from '../Common/Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/createsuccess.svg';
import { customAxios } from '@/lib/customAxios';

function MakeTeam(){
    const [ showModal, setShowModal ] = useState(false);

    const clickMakeTeam = async (e) => {
        e.preventDefault();

        const teamData = {
            limit: 0,
            owner: 0,
            status: true,
            title: e.target.title.value,
            category: e.target.category.value,
            numberofmembers: e.target.numberofmembers.value,
            introduction: e.target.introduction.value,
        }

        await customAxios.post('/make-team', teamData)
        console.log(teamData)
    }

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }
        return(
            <>
            <div className="makeTeamWrap">
                <h3>직접 팀을 만들어보세요!</h3>
                <form onSubmit={clickMakeTeam}>
                    <input className="teamName" type="text" name="title" placeholder="팀 이름" required></input>
                    <select name="category">
                        <option disabled selected hidden>주제 분류</option>
                        <option value="1">공부/학문</option>
                        <option value="2">운동/스포츠</option>
                        <option value="3">취미/오락</option>
                        <option value="4">취업/취준</option>
                        <option value="5">어학/자격증</option>
                        <option value="6">IT/개발</option>
                        <option value="7">기타</option>
                    </select>
                    <select name="numberofmembers">
                        <option disabled selected hidden>팀 인원</option>
                        <option value="1">1명</option>
                        <option value="2">2명</option>
                        <option value="3">3명</option>
                        <option value="4">4명</option>
                    </select>
                    <textarea className="teamIntro" name="introduction" placeholder="팀 소개글을 적어주세요!" required></textarea>
                    {/* form안의 내용이 작성,선택이 되어있을때만 openModal이 작동하게 if문을 추가해야됨 */}
                    <button className="basicBtn">팀 만들기</button>
                </form>

            </div>

            <DefaultModal
                    showModal={showModal}
                    imgSrc={ImgSrc}
                    title="짝짝짝! 당신의 팀이 만들어졌어요!"
                    contents="이제 다른 팀원들의 가입을 기다려주세요!"
                    btnOkText="팀 확인하러 가기"
                    closeModal={closeModal}
                    >
                </DefaultModal>
        
            </>
            )
    }
export default MakeTeam;