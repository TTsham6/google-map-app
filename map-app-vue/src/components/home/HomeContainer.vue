<template>
  <div>
    <div class="title">
      <p>地図アプリ（仮）</p>
    </div>
    <form novalidate @submit.prevent="fetchGoogleMap">
      <input v-model="latitude" type="number">
      <input v-model="longitude" type="number">
      <button type="submit">
        検索
      </button>
    </form>
    <div ref="googleMap" class="google-map" />
  </div>
</template>

<script setup lang="ts">
import { Loader } from "@googlemaps/js-api-loader";
import { Ref, onMounted, ref } from "vue";

// 定数
let map: google.maps.Map;
const googleMap = ref<HTMLElement>();
const zoom = 15;
const latitude: Ref<number> = ref(35.6809591);
const longitude: Ref<number> = ref(139.7673068);

const loader = new Loader({
  apiKey: process.env.VUE_APP_API_KEY,
  version: "weekly",
  libraries: ["places"],
});

onMounted(() => {
  fetchGoogleMap();
});

const fetchGoogleMap = () => {
  // オプション作成
  const mapOptions = {
    center: {
      lat: latitude.value,
      lng: longitude.value,
    },
    zoom,
  };

  // GoogleMap AMIを呼び出して、マップを描画する
  loader
    .load()
    .then((google) => {
      map = new google.maps.Map(googleMap.value as HTMLElement, mapOptions);
    })
    .catch((e) => {
      console.log(e);
    });
};

// Google Map範囲の座標を取得する
const getCoordinate = () => {

  const northEast = map.getBounds()?.getNorthEast();
  const southWest = map.getBounds()?.getSouthWest();

  const latLngBounds = {
    northEast: {
      lng: northEast?.lng(),
      lat: northEast?.lat()
    },
    southWest: {
      lng: southWest?.lng(),
      lat: southWest?.lat()
    }
  };
  // TODO バックエンドに送信
  console.log(latLngBounds);
};
</script>

<style lang="scss">
.google-map {
  height: 600px;
  width: 90%;
  margin: 0 auto;
}
</style>
