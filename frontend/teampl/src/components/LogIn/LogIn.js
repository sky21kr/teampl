import React from 'react';
import './LogIn.scss'

const LogIn = () => {
    return (
        <div className="logIn">
            <div className="logInTitle">
                팀플을 만들고 찾아보세요!
            </div>
            <input className="t-input" placeholder="아이디"/>
            <input className="t-input" placeholder="비밀번호"/>
            <button>로그인</button>
            <div>아직 회원이 아니신가요? 회원가입하기</div>
        </div>
    )
}

export default LogIn;