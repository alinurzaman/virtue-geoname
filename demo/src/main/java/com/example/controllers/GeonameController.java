package com.example.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.entities.Geoname;
import com.example.models.entities.GeonameProjection;
import com.example.services.GeonameService;

@RestController
@RequestMapping("/suggestions")
public class GeonameController {
    @Autowired
    private GeonameService geonameService;

    @GetMapping
    public List<GeonameProjection> getGeonameByNameLike(@RequestParam("q") Optional<String> nameQ,
            @RequestParam("latitude") Optional<String> latitude,
            @RequestParam("longitude") Optional<String> longitude) {

        double lat = Double.parseDouble(latitude.orElse("0"));
        double longi = Double.parseDouble(longitude.orElse("0"));
        String name = nameQ.orElse("");

        List<Geoname> list = new ArrayList<>();

        if (lat == 0 && longi == 0) {
            list = geonameService.findGeonameByName(name);
        } else {
            if (name == "")
                return null;
            list = geonameService.findGeonameByNameLatitudeLongitude(name, lat);
        }
        // list projection 232
        List<GeonameProjection> listProjection = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String country = "";

            if (list.get(i).getCountrycode() == "CA")
                country = "Canada";
            else
                country = "USA";
            String nameProjection = list.get(i).getName() + ", " + list.get(i).getAdmin1code() + ", " + country;
            GeonameProjection geonameProjection = new GeonameProjection(nameProjection,
                    list.get(i).getLatitude(), list.get(i).getLongitude(),
                    scoring(lat, longi, list.get(i).getLatitude(), list.get(i).getLongitude()));
            listProjection.add(geonameProjection);

            sortingScore(listProjection);

        }

        return listProjection;
    }

    public double scoring(double latQuery, double longiQuery, double latData, double longiData) {
        double scoreLat = 0;
        double scoreLongi = 0;

        // Scoring latitude
        if (latQuery == 0 || longiQuery == 0)
            return 1;
        if (latQuery - latData == 0)
            scoreLat = 0.5;
        else if (latQuery - latData > 0 && latQuery - latData <= 2)
            scoreLat = 0.45;
        else if (latQuery - latData > 2 && latQuery - latData <= 3)
            scoreLat = 0.35;
        else if (latQuery - latData > 3)
            scoreLat = 0.25;

        // Scoring longitude
        if (longiQuery - longiData == 0)
            scoreLongi = 0.5;
        else if (longiQuery - longiData > 0 && longiQuery - longiData <= 2)
            scoreLongi = 0.45;
        else if (longiQuery - longiData > 2 && longiQuery - longiData <= 3)
            scoreLongi = 0.35;
        else if (longiQuery - longiData > 3)
            scoreLongi = 0.25;
        else if (longiQuery < 0)
            scoreLongi = 0.05;

        return scoreLat + scoreLongi;
    }

    public Double sortingScore(List<GeonameProjection> listProjection) {
        Collections.sort(listProjection, new Comparator<GeonameProjection>() {
            @Override
            public int compare(GeonameProjection c1, GeonameProjection c2) {
                return Double.compare(c2.getScore(), c1.getScore());
            }
        });
        return null;
    }

}
