package org.googlemap.application.dto;

import lombok.Data;

@Data
public class CoordinateDto {

    private String placeName;

    private String placeType;

    private double latitude;

    private double longitude;
}
