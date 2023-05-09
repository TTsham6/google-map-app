package org.googlemap.application.dto;

import lombok.Data;

@Data
public class CoordinateDto {

    private String placeName;

    private String placeType;

    private double lat;

    private double lng;
}
