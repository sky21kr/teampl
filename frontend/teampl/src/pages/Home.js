import React from 'react';
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