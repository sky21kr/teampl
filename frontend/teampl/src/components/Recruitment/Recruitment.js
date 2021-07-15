// Import Swiper React components
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, {Pagination, Virtual} from 'swiper';
import React, { useEffect, useState, useLayoutEffect } from 'react';
import { customAxios } from '@/lib/customAxios';

// Import Swiper styles
import 'swiper/swiper.scss';
import 'swiper/components/pagination/pagination.scss';
import RecruitComponent from './RecruitComponent';

SwiperCore.use([Pagination, Virtual]);

function Recruitment(){

  const [ teamList, setTeamList ] = useState([])
  const [ pageNumber, setPageNumber ] = useState(0)

  useLayoutEffect(() => {
    if (teamList.length !== 0) { // plants가 빈 배열이 아니면'
      console.log('hi', teamList)
      const swiper = document.querySelector('.swiper-container').swiper;
      console.log(swiper)
      swiper.update();
      swiper.slideTo(0);
      // update 후 스크롤이 맨 처음 content 위치로 이동
    }
  }, [teamList]);

  useEffect(async () => {
    const fetchTeamList = (await customAxios.get('/team', { params: { pageNumber: 0, pageSize: 18}})).data.content
    const tempTeamList = []

    for(let i = 0 ; i < fetchTeamList.length / 6 ; i++) {
      tempTeamList.push(fetchTeamList.slice(i*6, (i+1)*6))
    }

    console.log('tempTeamList', tempTeamList)
    setTeamList(tempTeamList)
  },[pageNumber])

  const renderTeamList = teamList.map((team, index) => <SwiperSlide key={index}><RecruitComponent teamList={team}/></SwiperSlide>)

  return (
      <div className="recruitWrap">
      <h3>팀원을 구합니다</h3>
    <Swiper
      slidesPerView={1}
      pagination={{ clickable: true }}
    >
      {renderTeamList}
      {/* <SwiperSlide><RecruitComponent></RecruitComponent></SwiperSlide> */}
      {/* <SwiperSlide><RecruitComponent></RecruitComponent></SwiperSlide> */}
    </Swiper>
    </div>
  );
};

export default Recruitment;