import React from 'react';
import './LogIn.scss'
import { useHistory } from "react-router-dom";

const LogIn = () => {
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
            <button className="t-button" style={buttonStyle}>
                로그인
            </button>
            <div className="signUpPhrase">
                아직 회원이 아니신가요?
                <span onClick={moveSignUp}>
                    회원가입하기
                </span>
            </div>
        </div>
    )
}

export default LogIn;