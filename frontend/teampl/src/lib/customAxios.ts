import axios, { AxiosInstance } from 'axios';
import { SERVER_ADDRESS } from '@/config';

export const customAxios: AxiosInstance = axios.create({
    baseURL: `${SERVER_ADDRESS}`,
    headers: {
        token: sessionStorage.getItem('token'),
        userid: sessionStorage.getItem('userid'),
    },
})

customAxios.interceptors.request.use(
    function (config) {
        const token = sessionStorage.getItem('token')
        const userId = sessionStorage.getItem('userid')

        if(config.headers.token !== token) config.headers.token = token
        if(config.headers.userid !== userId) config.headers.userid = userId

        return config
    },
    function (error) {
        return Promise.reject(error);
    }
)