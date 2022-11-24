package com.example.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.entities.Geoname;
import com.example.models.entities.GeonameProjection;
import com.example.models.repos.GeonameRepo;

@Service
@Transactional
public class GeonameService {
    @Autowired
    private GeonameRepo geonameRepo;

    public List<Geoname> findGeonameByName(String name) {
        return geonameRepo.findGeonameByName("%" + name + "%");
    }

    public List<Geoname> findGeonameByNameLatitudeLongitude(String name, double lat) {
        return geonameRepo.findGeonameByNameLatitudeLongitude("%" + name + "%", lat);
    }

    public List<GeonameProjection> findGeonameByNameLike(String name) {
        return geonameRepo.findGeonameByNameLike("%" + name + "%");
    }
}
