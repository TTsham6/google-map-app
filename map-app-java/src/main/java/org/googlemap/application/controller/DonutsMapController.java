package org.googlemap.application.controller;

import org.googlemap.application.dto.CoordinateDto;
import org.googlemap.application.dto.LatLngBoundsDto;
import org.googlemap.domain.service.DonutsMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("donuts")
@CrossOrigin(origins = "http://localhost:8081")
public class DonutsMapController {

    @Autowired
    DonutsMapService donutsMapService;

    @RequestMapping("all")
    @ResponseBody
    public List<CoordinateDto> getAllDonutsMap(LatLngBoundsDto latLngBounds) {
        return this.donutsMapService.getAllDonutsMap(latLngBounds);
    }

    @RequestMapping("eatin")
    @ResponseBody
    public List<CoordinateDto> getEatInDonutsMap(LatLngBoundsDto latLngBounds) {
        return this.donutsMapService.getEatInDonutsMap(latLngBounds);
    }

    @RequestMapping("takeout")
    @ResponseBody
    public List<CoordinateDto> getTakeOutDonutsMap(LatLngBoundsDto latLngBounds){
        return this.donutsMapService.getTakeOutDonutsMap(latLngBounds);
    }
}
