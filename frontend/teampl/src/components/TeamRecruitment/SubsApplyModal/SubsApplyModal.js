import React, { useState } from 'react'
import './SubsApplyModal.scss'
import CloseBtn from '@/assets/images/close.svg'
import DefaultModal from '@/components/Common/Modal/DefaultModal/DefaultModal'

const SubsApplyModal = (props) => {

    const [ showModal, setShowModal ] = useState(false)



    const btnOk = () => {
        setShowModal(false)
    }

    const openModal = () => {
        props.closeModal()
        setShowModal(true)
    }

    const closeModal = () => {
        setShowModal(false)
    }

    return (
        <div>
            <div className={ props.showModal ? "defaultModalBackground" : "hidden"}>
                <div className="subsApplyModal">
                    <img className="closeBtn-a" onClick={props.closeModal} src={CloseBtn}/>
                    <div className="subsApplyTitle">
                        <b>{ props.teamName }</b> 팀에 가입을 신청합니다.
                    </div>
                    <textarea
                        placeholder="간단한 소개글을 작성해주세요!"
                        width="500"
                        height="300"
                        />
                    <button className="t-button" onClick={openModal}>가입신청하기</button>
                </div>
            </div>
            <DefaultModal
                showModal={showModal}
                title="가입 신청이 완료되었습니다!"
                contents="가입 승인을 기다려주세요! 승인 결과는 '나의 팀'에서 확인할 수 있습니다."
                btnOkText="메인화면으로"
                btnOk={btnOk}
                closeModal={closeModal}
        />
        </div>
    )
}

export default SubsApplyModal;