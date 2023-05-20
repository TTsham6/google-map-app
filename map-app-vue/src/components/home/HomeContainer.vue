<script setup lang="ts">
import { Loader } from "@googlemaps/js-api-loader";
import { Ref, onMounted, ref } from "vue";
import { BoundsCoordinate, Coordinate } from "@/types/type";
import { getDonuts } from "@/api/donut-map-api";
import { makeInfoWindowContent } from "@/util/dom-util"

// 定数
let map: google.maps.Map;
const googleMap = ref<HTMLElement>();
const zoom = 15;
const latitude: Ref<number> = ref(35.6809591);
const longitude: Ref<number> = ref(139.7673068);
const shopType: Ref<string> = ref("all");

const loader = new Loader({
  apiKey: process.env.VUE_APP_GOOGLE_API_KEY,
  version: "weekly",
  libraries: ["places"],
});

onMounted(async () => {
  map = await initializeMap() as google.maps.Map;
});

/**
 * Google Mapを描画する
 * @returns Google Mapオブジェクト
 */
const initializeMap = async () => {
  // オプション作成
  const mapOptions = {
    center: {
      lat: latitude.value,
      lng: longitude.value,
    },
    zoom
  };

  const google = await loader.load();
  const map = new google.maps.Map(googleMap.value as HTMLElement, mapOptions);
  return map;
};

/**
 * google map上にマーカーを作成する
 */
const drawMarkers = async () => {

  const boundsCoordinat = getMapBoundsCoord();

  const donuts = await getDonuts(boundsCoordinat, shopType.value) as Coordinate[];

  donuts.forEach(donutCoord => {
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
    <form novalidate @submit.prevent="initializeMap">
      <input v-model="latitude" type="number">
      <input v-model="longitude" type="number">
      <button type="submit">
        検索
      </button>
    </form>
    <div>
      <input type="radio" v-model="shopType" value="all" v-on:change="drawMarkers">
      <label for="all">すべてのドーナツ店を表示</label>

      <input type="radio" v-model="shopType" value="eatin" v-on:change="drawMarkers">
      <label for="eatin">イートイン可能な店だけ表示</label>

      <input type="radio" v-model="shopType" value="takeout" v-on:change="drawMarkers">
      <label for="takeout3">テイクアウト可能な店だけ表示</label>
    </div>
    <div>
      <button @click="drawMarkers">ドーナツショップを検索</button>
    </div>
    <div ref="googleMap" class="google-map" />
  </div>
</template>