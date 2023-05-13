<script setup lang="ts">
import { Loader } from "@googlemaps/js-api-loader";
import { Ref, onMounted, ref } from "vue";
import { BoundsCoordinate, Coordinate } from "@/types/type";
import { getEatInDonuts } from "@/api/donut-map-api";
import { makeInfoWindowContent } from "@/util/dom-util"

// 定数
let map: google.maps.Map;
const googleMap = ref<HTMLElement>();
const zoom = 15;
const latitude: Ref<number> = ref(35.6809591);
const longitude: Ref<number> = ref(139.7673068);

const loader = new Loader({
  apiKey: process.env.VUE_APP_GOOGLE_API_KEY,
  version: "weekly",
  libraries: ["places"],
});

onMounted(() => {
  drawGoogleMap();
});

/**
 * Google Mapを描画する
 */
const drawGoogleMap = async () => {
  // オプション作成
  const mapOptions = {
    center: {
      lat: latitude.value,
      lng: longitude.value,
    },
    zoom,
  };

  try {
    // GoogleMap APIを呼び出して、マップを描画する
    const google = await loader.load();
    map = new google.maps.Map(googleMap.value as HTMLElement, mapOptions);

    // イートイン可能なドーナツのマーカーを描画する
    drawEatInMarkers();

  } catch (error) {
    console.log(error);
  }
};

/**
 * イートイン可能なドーナツマップを描画する
 */
const drawEatInMarkers = async () => {
  // 現在のGoogle Mapの範囲を取得
  const boundsCoordinat = getMapBoundsCoord();

  // バックエンドから表示範囲内のドーナツ店のリストを取得
  const eatInDonuts = await getEatInDonuts(boundsCoordinat) as Coordinate[];

  // ドーナツ店の座標をGoogleMapに描画
  eatInDonuts.forEach(donutCoord => {

    const marker = new google.maps.Marker({
      position: new google.maps.LatLng(donutCoord.lat, donutCoord.lng),
      map
    });

    const infowinow = new google.maps.InfoWindow({
      content: makeInfoWindowContent(donutCoord),
    });

    marker.addListener("mouseover", () => {
      infowinow.open({
        anchor: marker,
        map
      });
    });

    marker.addListener("mouseout", () => {
      infowinow.close();
    });
  });
}

/**
 * Google Mapの東西南北それぞれの端の座標を返す
 * @returns Google Mapの東西南北それぞれの端の座標
 */
const getMapBoundsCoord = (): BoundsCoordinate => {
  const northEast = map.getBounds()?.getNorthEast();
  const southWest = map.getBounds()?.getSouthWest();

  const latLngBounds: BoundsCoordinate = {
    northLat: northEast?.lat() as number,
    southLat: southWest?.lat() as number,
    eastLng: northEast?.lng() as number,
    westLng: southWest?.lng() as number
  }

  return latLngBounds;
};


</script>

<style lang="scss">
.google-map {
  height: 600px;
  width: 90%;
  margin: 0 auto;
}
</style>

<template>
  <div>
    <div class="title">
      <p>地図アプリ（仮）</p>
    </div>
    <form novalidate @submit.prevent="drawGoogleMap">
      <input v-model="latitude" type="number">
      <input v-model="longitude" type="number">
      <button type="submit">
        検索
      </button>
    </form>
    <div>
      <button @click="getMapBoundsCoord">このエリアで再検索</button>
    </div>
    <div ref="googleMap" class="google-map" />
  </div>
</template>