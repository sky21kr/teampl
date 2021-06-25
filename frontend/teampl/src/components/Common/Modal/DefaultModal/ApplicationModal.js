import React from 'react'
import './ApplicationModal.scss'
import CloseBtn from '@/assets/images/close.svg'


const ApplicationModal = (props) => {

    ApplicationModal.defaultProps={
        name:"유저이름",
        application:"가입신청글"
    }

    return (
        <div className={ props.showModal ? "applicationModalBackground" : "hidden"}>
            <div className="applicationModal">
                <img className="closeBtn-a" onClick={props.closeModal} src={CloseBtn}/>
                <div className="modalTitle">{props.name} 님의 가입신청서</div>
               <textarea className="applicationBox">{props.application}</textarea>
               <div>
                <button className="t-button btnOk" onClick={props.btnOk}>승인</button>
                <button className="t-button btnCancel" onClick={props.btnCancel}>거절</button>
               </div>
            </div>
        </div>
    )
}

export default ApplicationModal;