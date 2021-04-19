import React from 'react'
import './DefaultModal.scss'
import CloseBtn from '@/assets/images/close.svg'


const DefaultModal = (props) => {

    return (
        <div className={ props.showModal ? "defaultModalBackground" : "hidden"}>
            <div className="defaultModal">
                <img className="closeBtn-a" onClick={props.closeModal} src={CloseBtn}/>
                {props.imgSrc ? <img className="modalImg" src={props.imgSrc}/> : '' }
                <div className="modalTitle">{props.title}</div>
                { props.contents ? <div className="modalContents">{props.contents}</div> : ''}
                { props.btnOkText ? <button className="t-button btnOk" onClick={props.btnOk}>{props.btnOkText}</button> : ''}
                { props.btnCancelText ? <button className="t-button btnCancel" onClick={props.btnCancel}>{props.btnCancelText}</button> : ''}
            </div>
        </div>
    )
}

export default DefaultModal;