// Import Swiper React components
import { Swiper, SwiperSlide} from 'swiper/react';
import SwiperCore, { Navigation } from 'swiper';
import { Link } from 'react-router-dom'

// Import Swiper styles
import 'swiper/swiper.scss';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';

SwiperCore.use([Navigation]);

function SubjectSlider (){
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
      <SwiperSlide><Link to="main"><img src="./images/subject01.png"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="main"><img src="./images/subject02.png"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="main"><img src="./images/subject03.png"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="main"><img src="./images/subject04.png"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="main"><img src="./images/subject05.png"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="main"><img src="./images/subject06.png"></img></Link></SwiperSlide>
      <SwiperSlide><Link to="main"><img src="./images/subject07.png"></img></Link></SwiperSlide>
      
    </Swiper>
    </div>
  );
};

export default SubjectSlider;