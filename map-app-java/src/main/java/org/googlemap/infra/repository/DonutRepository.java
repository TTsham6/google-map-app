package org.googlemap.infra.repository;

import org.googlemap.infra.model.Donut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonutRepository extends JpaRepository<Donut, Integer> {

    /**
     * donutテーブルからisInsideOKがTrueのレコードを抽出する
     * @return isInsideOKフラグがtrueのDonutのリスト
     */
    @Query("select d from Donut d where d.eatInOk = True")
    List<Donut> getEatInOkDonuts();
}
