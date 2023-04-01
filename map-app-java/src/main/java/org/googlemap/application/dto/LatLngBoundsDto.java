package org.googlemap.application.dto;

import lombok.Data;

@Data
public class LatLngBoundsDto {
    private double northLat;

    private double southLat;

    private double eastLng;

    private double westLng;
}
