import { Coordinate } from "@/types/type";

/**
 * HTMLタグを生成する
 * 
 * @param placeCoord 場所情報
 * @returns HTMLタグの文字列
 */
export const makeInfoWindowContent = (placeCoord: Coordinate): string => {
    const h2 = `<h2>${placeCoord.placeName}</h2>`

    const placeLabel =
        (placeCoord.placeType === 'donuts') ? 'ドーナツ店' :
            (placeCoord.placeType === 'park') ? '公園' :
                (placeCoord.placeType === 'coffee') ? 'コーヒーショップ' :
                    '';

    const h3 = `<h3>種別：${placeLabel}</h3>`

    return h2 + h3;
};