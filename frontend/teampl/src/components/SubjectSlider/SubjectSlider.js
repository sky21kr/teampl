// Import Swiper React components
import { Swiper, SwiperSlide} from 'swiper/react';
import SwiperCore, { Navigation } from 'swiper';
import { Link } from 'react-router-dom'
import { categoryCodeList } from '@/utils/CommonData/code';

// Import Swiper styles
import 'swiper/swiper.scss';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';

SwiperCore.use([Navigation]);

function SubjectSlider () {

  // const renderSlide = categoryCodeList.map((cateCode) =>
  //   <SwiperSlide><Link to={`team/search/${cateCode.code}`}><img src={`./images/subject0${cateCode.code}.png`} alt={`${codeName}`}></Link></img></Link></SwiperSlide>)

  return (
      <div className="subjectWrap">
    <h3>주제별로 팀 찾기</h3>
    <Swiper
        style={{width:'1200px'}}
      spaceBetween={20}
      slidesPerView={5}
      navigation
      onSlideChange={() => console.log('slide change')}
      onSwiper={(swiper) => console.log(swiper)}
    >
      <SwiperSlide><Link to="team/search/1"><img src="./images/subject01.png" alt="공부/학문"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="team/search/2"><img src="./images/subject02.png" alt="운동/스포츠"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="team/search/3"><img src="./images/subject03.png" alt="취미/오락"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="team/search/4"><img src="./images/subject04.png" alt="취업/취준"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="team/search/5"><img src="./images/subject05.png" alt="어학/자격증"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="team/search/6"><img src="./images/subject06.png" alt="IT/개발"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="team/search/7"><img src="./images/subject07.png" alt="기타"></img></Link></SwiperSlide>
      
    </Swiper>
    </div>
  );
};

export default SubjectSlider;