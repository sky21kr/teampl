import React from 'react'
import styled, { css } from 'styled-components';

const RecruitBox = ({ teamData }) => {
    const Subject = styled.span`
      text-align: center;
      font-size: 13px;
      padding: 3px 13px 5px;
      background:lightgray;
      ${props=>(props.category === 0 && css`
      background:#4461D7;`)
       || (props.category === 1 && css` 
      background: #59C894;`)
      || (props.category === 2 && css`
        background:#F45721;`)
      ||(props.category === 3 && css `
      background:#F03063;`)
      || (props.category === 4 && css`
      background:#FEB51F;`)
      ||(props.category === 5 && css`
      background:#895EF7;`)
      ||(props.category === 6 && css`
      background:#1FCBDC;
      `)}
      color: #fff;
    border-radius: 12px;
    `

    const Status = styled.span`
        padding: 4px 7px 2px;
        font-size: 11px;
        line-height: 12px;
        ${props =>(props.status === true && css`
        color: #ff3e3e;
        border: 1px solid #ff3e3e;`)
        ||(props.status === false && css`
        color: #c4c4c4;
        border: 1px solid #eeeeee;
        `)};
        background-color: #fff;
        border-radius: 13px;
    `


    // category: 2
    // datetime: "2021-05-29T11:37:54"
    // introduction: "7new"
    // limit: 4
    // numberofmembers: 4
    // status: true
    // teamid: 363
    // title: "7new"


    return (
        <div className="recruitBox">
            <div>
                <Subject {...teamData}>{teamData.category}</Subject>
                <Status {...teamData}>{teamData.status === true ? '모집중' : '모집완료'}</Status>
            </div>
            <div>
                <h3>{teamData.title}</h3>
                <p>{teamData.introduction}</p>
            </div>
            <div>
                <div>
                <span>{teamData.writer}</span>
                <span>{teamData.datetime}</span>
                </div>
                <span><img src="/images/message-circle.svg"></img>{teamData.comments}</span>
            </div>
        </div>
    )
}

export default RecruitBox;