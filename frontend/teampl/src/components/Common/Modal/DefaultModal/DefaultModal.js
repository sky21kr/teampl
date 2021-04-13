import React from 'react'
import './DefaultModal.scss'
import CloseBtn from '@/assets/images/close.svg'


const DefaultModal = (props) => {

    return (
        <div className={ props.showModal ? "defaultModalBackground" : "hidden"}>
            <div className="defaultModal">
                <img className="closeBtn-a" onClick={props.closeModal} src={CloseBtn}/>
                <img className="modalImg" src={props.imgSrc}/>
                <div className="modalTitle">{props.title}</div>
                { props.contents ? <div className="modalContents">{props.contents}</div> : ''}
                { props.btnOk ? <button className="t-button btnOk">{props.btnOk}</button> : ''}
                { props.btnCancel ? <button className="t-button btnCancel">{props.btnCancel}</button> : ''}
            </div>
        </div>
    )
}

export default DefaultModal;