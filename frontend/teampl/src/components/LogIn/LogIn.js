import React, { useState }from 'react';
import './LogIn.scss';
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal';
import { useHistory } from "react-router-dom";
import ImgSrc from '@/assets/images/askdelete.svg';

const LogIn = () => {

    const [ showModal, setShowModal ] = useState(false);

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    const btnOk = () => {
        history.push('')
    }

    let history = useHistory()

    const inputStyle = { width: '350px'}
    const buttonStyle = { marginTop: '40px', marginBottom: '40px'}


    const moveSignUp = () => {
        history.push('sign-up')
    }

    return (
        <div className="logIn">
            <div className="logInTitle">
                팀플을 만들고 찾아보세요!
            </div>
            <input className="t-input" style={inputStyle} placeholder="아이디"/>
            <input className="t-input" style={inputStyle} placeholder="비밀번호"/>
            <button onClick={openModal} className="t-button" style={buttonStyle}>
                로그인
            </button>
            <div className="signUpPhrase">
                아직 회원이 아니신가요?
                <span onClick={moveSignUp}>
                    회원가입하기
                </span>
            </div>
            <DefaultModal
                showModal={showModal}
                imgSrc={ImgSrc}
                title="로그인에 실패하셨습니다"
                contents="로그인에 실패"
                btnOkText="메인화면으로"
                btnOk={btnOk}
                btnCancelText="아니오"
                closeModal={closeModal}
            />
            

        </div>
    )
}

export default LogIn;