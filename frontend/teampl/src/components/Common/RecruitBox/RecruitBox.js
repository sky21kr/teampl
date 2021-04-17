import React from 'react'
import styled, { css } from 'styled-components';

const RecruitBox = (props) => {

    const type = props.type
    const status = props.status
    const title = props.title
    const contents = props.contents
    const writer = props.writer
    const date = props.date
    const comments = props.comments

    RecruitBox.defaultProps={
        type : "카테고리",
        status : "모집정보",
        title: "글 제목",
        contents : "글 내용",
        writer : "글쓴이",
        date : "날짜",
        comments : "댓글 수"
    }

    const Subject = styled.span`
      text-align: center;
      font-size: 13px;
      padding: 3px 13px 5px;
      background:lightgray;
      ${props=>props.type == "공부/학문" && css`
      background:#4461D7;`
       || props.type == "취업/취준" && css` 
      background: #59C894;`
      || props.type=="운동/스포츠" && css`
        background:#F45721;`
      ||props.type == "취미/오락" && css `
      background:#F03063;`
      ||props.type == "어학/자격증" && css`
      background:#FEB51F;`
      ||props.type == "IT/개발" && css`
      background:#895EF7;`
      ||props.type == "기타" && css`
      background:#1FCBDC;
      `}
      color: #fff;
    border-radius: 12px;
    `

    const Status = styled.span`
        padding: 4px 7px 2px;
        font-size: 11px;
        line-height: 12px;
        ${props =>props.status == "모집중" && css`
        color: #ff3e3e;
        border: 1px solid #ff3e3e;`
        ||props.status == "모집완료" && css`
        color: #c4c4c4;
        border: 1px solid #eeeeee;
        `};
        background-color: #fff;
        border-radius: 13px;
    `



    return (
        <div className="recruitBox">
            <div>
                <Subject {...props}>{type}</Subject>
                <Status {...props}>{status}</Status>
            </div>
            <div>
                <h3>{title}</h3>
                <p>{contents}</p>
            </div>
            <div>
                <div>
                <span>{writer}</span>
                <span>{date}</span>
                </div>
                <span><img src="/images/message-circle.svg"></img>{comments}</span>
            </div>
        </div>
    )
}

export default RecruitBox;