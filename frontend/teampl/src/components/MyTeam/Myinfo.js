import React, { Component, useState } from 'react';
import DeleteModal from './DeleteModal';
import ChangeModal from './ChangeModal';
import './MyTeam.scss'

function Myinfo(props){

    // 이름변경하기 모달
    let [changeModal,changeModalHandler] = useState(false);
 
    const openChangeModal= ()=>{
        changeModalHandler(true);
    }

    const closeChangeModal=()=>{
        changeModalHandler(false);
    }

    // 계정삭제하기 모달
    
    let [deleteModal,deleteModalHandler] = useState(false);
 
    const openDeleteModal= ()=>{
        deleteModalHandler(true);
    }

    const closeDeleteModal=()=>{
        deleteModalHandler(false);
    }

    const userName = props.userName
    const madeTeamNum = props.madeTeamNum
    const joinedTeamNum = props.joinedTeamNum

    Myinfo.defaultProps={
        userName : "유저이름",
        madeTeamNum : "N",
        joinedTeamNum : "N"
    }

    return(
        <>
        <div className="myInfoWrap">
            <div>
                 <img src="./images/userimage.png"></img>

                 <div>
                 <h2>{userName}  <button onClick={openChangeModal}>이름변경</button></h2>
                 <p>내가만든 팀플 {madeTeamNum}개   |   내가 가입한 팀플 {joinedTeamNum}개</p>
                 </div>
            </div>

            <button onClick={openDeleteModal} >팀플 탈퇴하기</button>
        </div>
    
        <ChangeModal open={changeModal} close={closeChangeModal}></ChangeModal>
        <DeleteModal open={deleteModal} close={closeDeleteModal}></DeleteModal>
    </>
    )
}

export default Myinfo;
