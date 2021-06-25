import React from 'react';
import MakeTeam from '../components/MakeTeam/MakeTeam';
import Recruitment from '../components/Recruitment/Recruitment';
import SearchArea from '../components/SearchArea/SearchArea';
import SubjectSlider from '../components/SubjectSlider/SubjectSlider';


const Home = () => {
    return (
        <div>
            <SearchArea></SearchArea>
            <SubjectSlider></SubjectSlider>
            <Recruitment></Recruitment>
        </div>
    )
}

export default Home;