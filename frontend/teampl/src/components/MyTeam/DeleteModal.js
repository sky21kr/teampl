import React from 'react';

const DeleteModal= (props)=>{
    const {open,close,header}=props;

    return(
            <div className={open ? 'openModal modal':'closeModal'} >
            { open? (
                <>
                <div className="modalBg" onClick={close}></div>
                    <div className="makeTeamModal">
                    <img src="/images/askdelete.svg"></img>
                    <img className="closeBtn" onClick={close} src="/images/close.svg"></img>
                    <h3>정말로 계정을 삭제하시겠습니까?</h3>
                    <button className="yesBtn" onClick={close}>예, 삭제하겠습니다.</button>
                    <button className= "noBtn" onClick={close}>아니요, 한번 더 생각해볼게요.</button>
                </div>
                </>
                ):null}

        </div>
    )
}

export default DeleteModal;