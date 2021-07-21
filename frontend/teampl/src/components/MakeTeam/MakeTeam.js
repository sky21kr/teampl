import React, { Component, useState } from 'react';
import './MakeTeam.scss'
import DefaultModal from '../Common/Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/createsuccess.svg';
import failImgSrc from '@/assets/images/askdelete.svg';
import { customAxios } from '@/lib/customAxios';
import SelectBox from '@/components/Common/SelectBox/SelectBox'
import { categoryCodeList } from '@/utils/CommonData/code';

function MakeTeam(){
    const [ showModal, setShowModal ] = useState(false);
    const [ showAlertModal, setShowAlertModal ] = useState(false);

    const [ teamData, setTeamData ] = useState({
        owner: window.sessionStorage.getItem('userid'),
        status: true,
        numberofmembers: 1,
    })

    const clickMakeTeam = async (e) => {
        e.preventDefault();

        if( !teamData.title || !teamData.limit || !teamData.introduction || !teamData.category ) {
            setShowAlertModal(true)
            return
        } 
        
        await customAxios.post('/make-team', teamData)
        openModal();
    }

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    const changeCateSelect = (cate) => {
        setTeamData({
            ...teamData,
            category: cate.code
        })
    }

    const changeMemberSelect = (member) => {
        setTeamData({
            ...teamData,
            limit: member.value
        })
    }

    const changeTeamInfo = (e) => {
        setTeamData({
            ...teamData,
            [e.target.name]: e.target.value
        })
    }

    const categorySelectOption = categoryCodeList.map((cateCode) => ({...cateCode, value: cateCode.code})).slice(1)
    const memberSelectOption = Array.from({length: 4}, (v, i) => ({ value: i+1, label: `${i+1}명`}))

        return(
            <>
            <div className="makeTeamWrap">
                <h3>직접 팀을 만들어보세요!</h3>
                <input
                    className="teamName"
                    onChange={changeTeamInfo}
                    type="text"
                    name="title"
                    placeholder="팀 이름"/>
                <SelectBox
                    className="cateSelectBox"
                    placeholder="주제 분류"
                    options={categorySelectOption}
                    onChange={changeCateSelect}
                />
                <SelectBox
                    className="memberSelectBox"
                    placeholder="팀 인원 "
                    options={memberSelectOption}
                    onChange={changeMemberSelect}
                />
                <textarea
                    className="teamIntro"
                    onChange={changeTeamInfo}
                    name="introduction"
                    placeholder="팀 소개글을 적어주세요!"
                    required/>
                <button onClick={clickMakeTeam} className="t-button">팀 만들기</button>
            </div>

            <DefaultModal
                showModal={showModal}
                imgSrc={ImgSrc}
                title="짝짝짝! 당신의 팀이 만들어졌어요!"
                contents="이제 다른 팀원들의 가입을 기다려주세요!"
                btnOkText="팀 확인하러 가기"
                closeModal={closeModal}/>
            <DefaultModal
                showModal={showAlertModal}
                imgSrc={failImgSrc}
                title="정보를 모두 입력해주세요."
                btnOkText="확인"
                btnOk={() => setShowAlertModal(false)}
                closeModal={() => setShowAlertModal(false)}/>
            </>
        )
    }
export default MakeTeam;