import React, { useState }from 'react';
import './LogIn.scss';
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal';
import { useHistory } from "react-router-dom";
import ImgSrc from '@/assets/images/askdelete.svg';
import { customAxios } from '@/lib/customAxios';

const LogIn = () => {

    const [ showModal, setShowModal ] = useState(false);
    const [ loginInfo, setLoginInfo ] = useState({
        id: "",
        password: "",
    });

    const { id, password } = loginInfo

    const failLogin = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    const btnOk = () => {
        closeModal()
    }

    let history = useHistory()

    const inputStyle = { width: '350px'}
    const buttonStyle = { marginTop: '40px', marginBottom: '40px'}


    const moveSignUp = () => {
        history.push('sign-up')
    }

    const successLogin = () => {
        history.push('')
    }

    const changeInput = (e) => {
        const { name, value } = e.target

        const nextLoginInfo = {
            ...loginInfo,
            [name]: value,
        }

        setLoginInfo(nextLoginInfo)
    }

    const clickLogin = async (loginInfo) => {
        try {
            const result = await customAxios.post('/login', loginInfo)
            window.sessionStorage.setItem('token', result.data.token);
            window.sessionStorage.setItem('userid', result.data.userId);
            successLogin()
        } catch {
            failLogin()
        }
    }

    return (
        <div className="logIn">
            <div className="logInTitle">
                팀플을 만들고 찾아보세요!
            </div>
            <input className="t-input" name="id" value={id} onChange={changeInput} style={inputStyle} placeholder="아이디"/>
            <input className="t-input" type="password" name="password" value={password} onChange={changeInput} style={inputStyle} placeholder="비밀번호"/>
            <button onClick={() => clickLogin(loginInfo)} className="t-button" style={buttonStyle}>
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
                contents="아이디와 비밀번호를 다시 확인해주세요"
                btnOkText="확인"
                btnOk={btnOk}
                closeModal={closeModal}
            />
        </div>
    )
}

export default LogIn;