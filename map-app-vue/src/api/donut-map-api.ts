import { BoundsCoordinate, DonutsCoordinate } from "@/types/type";
import axios, { AxiosRequestConfig, isAxiosError } from "axios"

const TIME_OUT = 15000;
const BASE_URL = process.env.VUE_BASE_URL;

/**
 * バックエンドからドーナツ店の座標リストを取得する
 * @param boundCoordinate  
 * @returns
 */
export const getEatInDonuts = async (boundCoordinate: BoundsCoordinate) => {
    const config: AxiosRequestConfig = {
        url: `${BASE_URL}:8080/donuts/eatin`,
        method: "GET",
        timeout: TIME_OUT,
        params: boundCoordinate
    }

    try {
        const response = await axios<DonutsCoordinate[]>(config);
        return response.data
    } catch (error) {
        if (isAxiosError(error) && error.response && error.response.status) {
            console.log(error.message)
        }
    }
}