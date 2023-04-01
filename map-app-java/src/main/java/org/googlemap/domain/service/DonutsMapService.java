package org.googlemap.domain.service;

import org.googlemap.application.dto.CoordinateDto;
import org.googlemap.application.dto.LatLngBoundsDto;
import org.googlemap.domain.util.DtoEntityConverter;
import org.googlemap.infra.model.Coordinate;
import org.googlemap.infra.model.Donut;
import org.googlemap.infra.repository.DonutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 座標取得サービス
 */
@Service
public class DonutsMapService {

    @Autowired
    DonutRepository donutRepository;

    @Autowired
    DtoEntityConverter converter;

    /**
     * イートインが可能なドーナツ店を取得する
     *
     * @return イートインが可能なドーナツ店の座標リスト
     */
    public List<CoordinateDto> getEatInDonutsMap(){
        // イートインが可能なドーナツ店を取得する
        List<Donut> eatInDonutsList = this.donutRepository.getEatInOkDonuts();

        // EntityをDTOに変換
        List<CoordinateDto> coordinateList = this.converter
                .convertDonutListToCoordinateList(eatInDonutsList);

        return coordinateList;
    }

    /***
     * イートインが可能なドーナツ店を取得する
     *
     * @param latLngBounds Google Mapの南北の緯度の範囲と東西の経度の範囲
     * @return イートインが可能なドーナツ店の座標リスト
     */
    public List<CoordinateDto> getEatInDonutsMap(LatLngBoundsDto latLngBounds) {

        List<Donut> eatInDonutsList = this.donutRepository.getEatInOkDonuts(latLngBounds.getNorthLat(),
                        latLngBounds.getSouthLat(),
                        latLngBounds.getEastLng(),
                        latLngBounds.getWestLng());

        List<CoordinateDto> coordinateList = this.converter
                .convertDonutListToCoordinateList(eatInDonutsList);

        return coordinateList;
    }


}
