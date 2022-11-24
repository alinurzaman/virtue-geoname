package com.example.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Geoname;
import com.example.models.entities.GeonameProjection;

public interface GeonameRepo extends JpaRepository<Geoname, Long> {

    @Query("SELECT g FROM Geoname g WHERE g.name LIKE :name")
    public List<Geoname> findGeonameByName(@PathParam("name") String name);

    @Query("SELECT g FROM Geoname g WHERE g.name LIKE :name AND g.latitude <= :lat")
    public List<Geoname> findGeonameByNameLatitudeLongitude(@PathParam("name") String name,
            @PathParam("lat") double lat);

    public List<GeonameProjection> findGeonameByNameLike(@PathParam("name") String name);
}
