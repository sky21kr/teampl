import React from 'react';
import './SignUp.scss'

const SignUp = () => {

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
            <button className="t-button" style={buttonStyle}>
                회원가입하기
            </button>
        </div>
    )
}

export default SignUp;