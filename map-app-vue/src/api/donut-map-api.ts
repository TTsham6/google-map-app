import { BoundsCoordinate, Coordinate } from "@/types/type";
import axios, { AxiosRequestConfig, isAxiosError } from "axios"

const TIME_OUT = 15000;
const BASE_URL = process.env.VUE_APP_BASE_URL;

/**
 * バックエンドから
 * @param boundCoordinate 
 * @param shopType 
 * @returns 
 */
export const getDonuts = async (boundCoordinate: BoundsCoordinate, shopType: string) => {
    const config: AxiosRequestConfig = {
        url: `http://${BASE_URL}:8080/donuts/${shopType}`,
        method: "GET",
        timeout: TIME_OUT,
        params: boundCoordinate
    }

    try {
        const response = await axios<Coordinate[]>(config);
        console.log(response);
        return response.data
    } catch (error) {
        if (isAxiosError(error) && error.response && error.response.status) {
            console.log(error.message)
        }
    }
}