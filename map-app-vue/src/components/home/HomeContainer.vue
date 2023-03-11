<template>
  <div>
    <div class="title">
      <p>地図アプリ（仮）</p>
    </div>
    <form novalidate @submit.prevent="fetchGoogleMap">
      <input type="number" v-model="latitude" />
      <input type="number" v-model="longitude" />
      <button type="submit">検索</button>
    </form>
    <div ref="googleMap" class="google-map" />
  </div>
</template>

<script setup lang="ts">
import { Loader } from "@googlemaps/js-api-loader";
import { Ref, onMounted, onUpdated, ref } from "vue";

// 定数
const googleMap = ref<HTMLElement>();
const zoom = 15;

let latitude: Ref<number> = ref(35.6809591);
let longitude: Ref<number> = ref(139.7673068);

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
    .then((google: any) => {
      new google.maps.Map(googleMap.value, mapOptions);
    })
    .catch((e: any) => {
      console.log(e);
    });
};
</script>

<style lang="scss">
.google-map {
  height: 600px;
  width: 90%;
  margin: 0 auto;
}
</style>
