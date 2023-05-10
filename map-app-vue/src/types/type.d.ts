export interface Coordinate {
    placeName: string,
    placeType: string,
    lat: number,
    lng: number
}

export interface BoundsCoordinate {
    northLat: number,
    southLat: number,
    eastLng: number,
    westLng: number
}

export interface DonutsCoordinate {
    placeName: string,
    lat: number,
    lng: number
}
