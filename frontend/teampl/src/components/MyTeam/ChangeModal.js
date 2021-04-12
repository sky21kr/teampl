import React from 'react';

const ChangeModal= (props)=>{
    const {open,close,header}=props;

    return(
            <div className={open ? 'openModal modal':'closeModal'} >
            { open? (
                <>
                <div className="modalBg" onClick={close}></div>
                    <div className="makeTeamModal">
                    <h3>이름변경하기</h3>
                    <img className="closeBtn" onClick={close} src="/images/close.svg"></img>
                    <button onClick={close}>이름변경</button>
                </div>
                </>
                ):null}

        </div>
    )
}

export default ChangeModal;