import React, { useState } from 'react'
import './SubsApplyModal.scss'
import CloseBtn from '@/assets/images/close.svg'
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal'
import { customAxios } from '@/lib/customAxios'

const SubsApplyModal = ({ teamId, closeModal, showModal, teamName }) => {
    const [ showOkModal, setShowOkModal ] = useState(false)
    const [ introduction, setIntroduction] = useState('')

    const closeOkModal = () => {
        setShowOkModal(false)
    }

    const clickApply = () => {
        handleApply()
    }

    const handleApply = async () => {
        const params = {
            contents: introduction,
            teamid: Number(teamId),
            userid: Number(sessionStorage.getItem('userid'))
        }

        await customAxios.post('/application', params)

        closeModal()
        setIntroduction('')
        setShowOkModal(true)
    }

    const changeIntro = (e) => {
        setIntroduction(e.target.value)
    }

    return (
        <div>
            <div className={ showModal ? "defaultModalBackground" : "hidden"}>
                <div className="subsApplyModal">
                    <img className="closeBtn-a" onClick={closeModal} src={CloseBtn}/>
                    <div className="subsApplyTitle">
                        <b>{ teamName }</b> 팀에 가입을 신청합니다.
                    </div>
                    <textarea
                        onChange={changeIntro}
                        placeholder="간단한 소개글을 작성해주세요!"
                        width="500"
                        height="300"
                        />
                    <button className="t-button" onClick={clickApply}>가입신청하기</button>
                </div>
            </div>
            <DefaultModal
                showModal={showOkModal}
                title="가입 신청이 완료되었습니다!"
                contents="가입 승인을 기다려주세요! 승인 결과는 '나의 팀'에서 확인할 수 있습니다."
                btnOkText="확인"
                btnOk={closeOkModal}
                closeModal={closeOkModal}
        />
        </div>
    )
}

export default SubsApplyModal;