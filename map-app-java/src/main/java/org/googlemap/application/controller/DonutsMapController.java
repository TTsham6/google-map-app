package org.googlemap.application.controller;

import org.googlemap.application.dto.CoordinateDto;
import org.googlemap.domain.service.DonutsMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("donuts")
public class DonutsMapController {

    @Autowired
    DonutsMapService donutsMapService;

    @RequestMapping("eatin")
    @ResponseBody
    public List<CoordinateDto> getEatInDonutsMap() {
        return this.donutsMapService.getEatInDonutsMap();
    }


}
