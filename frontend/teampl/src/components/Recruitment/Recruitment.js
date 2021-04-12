// Import Swiper React components
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, {Pagination} from 'swiper';

// Import Swiper styles
import 'swiper/swiper.scss';
import 'swiper/components/pagination/pagination.scss';
import RecruitComponent from './RecruitComponent';

SwiperCore.use([Pagination]);

function Recruitment(){
  return (
      <div className="recruitWrap">
      <h3>팀원을 구합니다</h3>
    <Swiper
      spaceBetween={50}
      slidesPerView={1}
      pagination={{ clickable: true }}
    >
      <SwiperSlide><RecruitComponent></RecruitComponent></SwiperSlide>
      <SwiperSlide><RecruitComponent></RecruitComponent></SwiperSlide>
      <SwiperSlide><RecruitComponent></RecruitComponent></SwiperSlide>
    </Swiper>
    </div>
  );
};

export default Recruitment;