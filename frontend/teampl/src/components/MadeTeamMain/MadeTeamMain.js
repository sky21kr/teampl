import React, { Component, useState, useEffect } from 'react';
import styled, { css } from 'styled-components';
import beforebtn from '@/assets/images/beforebtn.svg';
import teammaster from '@/assets/images/teammaster.svg';
import teammember from '@/assets/images/teammember.svg';
import DefaultModal from '../Common/Modal/DefaultModal/DefaultModal';
import ImgSrc from '@/assets/images/askdelete.svg';
import './MadeTeamMain.scss'
import { useHistory } from "react-router-dom";
import Posting from '@/components/Common/Posting/Posting';
import PostingModal from '../Common/Modal/DefaultModal/PostingModal';
import { Link } from 'react-router-dom';
import MemberModal from './MemberManageModal/MemberModal';
import { customAxios } from '@/lib/customAxios';

const MadeTeamMain = ({match}) => {
  const teamId = match.params.teamId

    useEffect(() => {
      async function fetchTeamDetail() {
        // await customAxios.get('/team')
      }
      fetchTeamDetail()
    },[])

    // const type = props.type
    // const teamName = props.teamName
    let history = useHistory()

    MadeTeamMain.defaultProps={
        type : "카테고리",
        teamName : "팀 이름"
    }

    const [ showModal, setShowModal ] = useState(false);

    const openModal = () => {
        setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    // 글쓰기모달
    const [ showModal2, setShowModal2 ] = useState(false);

    const openModal2 = () => {
        setShowModal2(true);
    }

    const closeModal2 = () => {
        setShowModal2(false);
    }

    const [ showModal3, setShowModal3 ] = useState(false);

    const openModal3 = () => {
        setShowModal3(true);
    }

    const closeModal3 = () => {
        setShowModal3(false);
    }


    
    const Subject = styled.span`
      text-align: center;
      font-size: 13px;
      padding: 3px 13px 5px;
      background:lightgray;
      ${props=>(props.type === "공부/학문" && css`
      background:#4461D7;`)
       || (props.type === "취업/취준" && css` 
      background: #59C894;`)
      || (props.type==="운동/스포츠" && css`
        background:#F45721;`)
      ||(props.type === "취미/오락" && css `
      background:#F03063;`)
      ||(props.type === "어학/자격증" && css`
      background:#FEB51F;`)
      ||(props.type === "IT/개발" && css`
      background:#895EF7;`)
      ||(props.type === "기타" && css`
      background:#1FCBDC;
      `)}
      color: #fff;
    border-radius: 12px;
    `

    return(
      <>

        <div className="titleArea">
            <>
            <img onClick={()=>{history.push('/team')}} src={beforebtn}/>
            {/* <h2>{teamName}</h2> */}
            {/* <Subject className="subjectBtn" {...props}>{type}</Subject>
            <div>
              <img src={teammaster}/>
              <img src={teammember}/>
              <img src={teammember}/>
              <img src={teammember}/>
            </div> */}
            <Link to="/team/modify"><button className="leaveBtn">정보 수정하기</button></Link>
            <button className="leaveBtn" onClick={openModal3}>멤버 관리</button>
            <button className="leaveBtn" onClick={openModal}>팀 삭제하기</button>
            </>

            <button className="writeBtn" onClick={openModal2}>글쓰기</button>


            <DefaultModal
                    showModal={showModal}
                    imgSrc={ImgSrc}
                    title="정말로 팀을 삭제하시겠습니까?"
                    btnOkText="네, 삭제하겠습니다."
                    btnCancelText="아니오, 한번 더 생각해볼게요"
                    closeModal={closeModal}
                    >
                </DefaultModal>


                <MemberModal
                  teamId={teamId}
                  showModal={showModal3}
                  closeModal={closeModal3}/>
                <PostingModal
                    className="postMode"
                    showModal={showModal2}
                    title="글쓰기"
                    closeModal={closeModal2}
                    btnOkText="등록하기"
                    >
                </PostingModal>


        </div>

        <div className="postingWrap">
          <Posting title={"글자수제한 확인하기 위한 가제입니다 가나다라마바사"} writer={"뽕뽕이"} date={"2021-04-21"} time={"13:28:23"} content={"적당한 모바일 화면에서는 잘 되는 것 같지만, 반응형 대응이 잘 안되어 마음에 썩 들지 않았다.화면이 큰 모바일의 경우 텍스트들이 한줄이 되는데도 불구하고 더보기 버튼이 생성되게 되는데, 이런 경우에는 더보기 버튼은 없어지고 내용이 다 보여야 한다고 생각했다.적당한 모바일 화면에서는 잘 되는 것 같지만, 반응형 대응이 잘 안되어 마음에 썩 들지 않았다.화면이 큰 모바일의 경우 텍스트들이 한줄이 되는데도 불구하고 더보기 버튼이 생성되게 되는데, 이런 경우에는 더보기 버튼은 없어지고 내용이 다 보여야 한다고 생각했다."} comments={"3"}></Posting>
          <Posting></Posting>
          <Posting></Posting>
          <Posting></Posting>
          <Posting></Posting>

        </div>

        <div className="pagenation">페이지네이션</div>

      </>

    )

}

export default MadeTeamMain;