import axios, { AxiosInstance } from 'axios';
import { SERVER_ADDRESS } from '@/config';
// import cookies from 'js-cookie';

export const customAxios: AxiosInstance = axios.create({
    baseURL: `${SERVER_ADDRESS}`,
    // headers: {
    //     access_token: cookies.get('access_token'),
    // },
})