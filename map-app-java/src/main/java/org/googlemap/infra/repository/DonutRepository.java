package org.googlemap.infra.repository;

import org.googlemap.application.dto.LatLngBoundsDto;
import org.googlemap.infra.model.Donut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonutRepository extends JpaRepository<Donut, Integer> {

    /**
     * donutsテーブルから座標範囲内のドーナツレコードを全て取得する
     *
     * @param northLat　最北端の緯度
     * @param southLat 最南端の緯度
     * @param eastLng 最東端の経度
     * @param westLng 最西端の経度
     * @return 緯度・経度の範囲内の座標リスト
     */
    @Query("select d from Donut d " +
            "where d.lat < :northLat " +
            "and d.lat > :southLat " +
            "and d.lng < :eastLng " +
            "and d.lng > :westLng")
    List<Donut> getAllDonuts(@Param("northLat") double northLat,
                             @Param("southLat") double southLat,
                             @Param("eastLng") double eastLng,
                             @Param("westLng") double westLng);

    /**
     * donutテーブルからeatInOkがTrueのレコードを抽出する
     *
     * @param northLat　最北端の緯度
     * @param southLat 最南端の緯度
     * @param eastLng 最東端の経度
     * @param westLng 最西端の経度
     * @return 緯度・経度の範囲内の座標リスト
     */
    @Query("select d from Donut d " +
            "where d.eatInOk = true " +
                "and d.lat < :northLat " +
                "and d.lat > :southLat " +
                "and d.lng < :eastLng " +
                "and d.lng > :westLng")
    List<Donut> getEatInOkDonuts(@Param("northLat") double northLat,
                                 @Param("southLat") double southLat,
                                 @Param("eastLng") double eastLng,
                                 @Param("westLng") double westLng);

    /**
     * donutテーブルからtakeOutOkがTrueのレコードを抽出する
     *
     * @param northLat　最北端の緯度
     * @param southLat 最南端の緯度
     * @param eastLng 最東端の経度
     * @param westLng 最西端の経度
     * @return 緯度・経度の範囲内の座標リスト
     */
    @Query("select d from Donut d " +
            "where d.takeOutOk = true " +
                "and d.lat < :northLat " +
                "and d.lat > :southLat " +
                "and d.lng < :eastLng " +
                "and d.lng > :westLng")
    List<Donut> getTakeOutOkDonuts(@Param("northLat") double northLat,
                                   @Param("southLat") double southLat,
                                   @Param("eastLng") double eastLng,
                                   @Param("westLng") double westLng);
}
