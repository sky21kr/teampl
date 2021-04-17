import React from 'react';
import './RecruitComponent.scss';
import RecruitBox from '@/components/Common/RecruitBox/RecruitBox';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

function RecruitComponent(){
    return(
        <div className="recruitBoxWrap">
            <Link to="main" style={{textDecoration:'none'}}>
                <RecruitBox></RecruitBox>
            </Link>

            <Link to="main" style={{textDecoration:'none'}}>
             <RecruitBox type={"취업/취준"} status={"모집완료"}></RecruitBox>
            </Link>
            
            <Link to="main" style={{textDecoration:'none'}}>
             <RecruitBox type={"운동/스포츠"} status={"모집중"}></RecruitBox>
            </Link>

            <Link to="main" style={{textDecoration:'none'}}>
              <RecruitBox type={"공부/학문"} status={"모집중"}></RecruitBox>
            </Link>

            <Link to="main" style={{textDecoration:'none'}}>
                <RecruitBox type={"어학/자격증"} status={"모집중"}></RecruitBox>
            </Link>

            <Link to="main" style={{textDecoration:'none'}}>
               <RecruitBox type={"취미/오락"} status={"모집중"}></RecruitBox>   
            </Link>

        </div>
    );
}

export default RecruitComponent;