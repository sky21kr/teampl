import React, { useState } from 'react';
import './SignUp.scss'
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal'
import imgSrc from '@/assets/images/signupsuccess.svg'
import { useHistory } from "react-router-dom";

const SignUp = () => {
    
    const history = useHistory()

    const [ showModal, setShowModal ] = useState(false)

    const openModal = () => {
        setShowModal(true)
    }

    const closeModal = () => {
        setShowModal(false)
    }

    const btnOk = () => {
        history.push('')
    }

    const inputStyle = { width: '350px'}
    const buttonStyle = { marginTop: '40px', marginBottom: '40px'}

    return (
        <div className="signUp">
            <div className="signUpTitle">
                팀플을 만들고 찾아보세요!
            </div>
            <input className="t-input" style={inputStyle} placeholder="아이디"/>
            <input className="t-input" style={inputStyle} placeholder="닉네임(한글, 영문, 숫자)"/>
            <input className="t-input" style={inputStyle} placeholder="비밀번호"/>
            <button className="t-button" style={buttonStyle} onClick={openModal}>
                회원가입하기
            </button>
            <DefaultModal
                showModal={showModal}
                imgSrc={imgSrc}
                title="가입완료! 반가워요!"
                contents="당신이 원하는 팀을 찾거나 직접 만들어봐요!"
                btnOkText="메인화면으로"
                btnOk={btnOk}
                closeModal={closeModal}
            />
        </div>
    )
}

export default SignUp;