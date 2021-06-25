import axios, { AxiosInstance } from 'axios';
import { SERVER_ADDRESS } from '@/config';
import Cookies from 'js-cookie';

export const customAxios: AxiosInstance = axios.create({
    baseURL: `${SERVER_ADDRESS}`,
    headers: {
        access_token: Cookies.get('token'),
    },
})