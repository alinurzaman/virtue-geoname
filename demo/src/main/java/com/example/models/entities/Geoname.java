package com.example.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geoname")
public class Geoname implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "ascii", length = 200)
    private String asciinames;

    @Column(name = "alt_name", length = 5000)
    private String alternatenames;

    @Column(name = "lat")
    private double latitude;

    @Column(name = "long")
    private double longitude;

    @Column(name = "feat_class")
    private char featureclass;

    @Column(name = "feat_code", length = 10)
    private String featurecode;

    @Column(name = "country", length = 2)
    private String countrycode;

    @Column(name = "cc2", length = 60)
    private String cc2;

    @Column(name = "admin1", length = 20)
    private String admin1code;

    @Column(name = "admin2", length = 80)
    private String admin2code;

    @Column(name = "admin3", length = 20)
    private String admin3code;

    @Column(name = "admin4", length = 20)
    private String admin4code;

    @Column(name = "population")
    private Long population;

    @Column(name = "elevation")
    private double elevation;

    @Column(name = "dem")
    private int dem;

    @Column(name = "tz", length = 40)
    private String tz;

    @Column(name = "modified_at")
    private LocalDate modificationdate;

    public Geoname() {
    }

    public Geoname(Long id, String name, String asciinames, String alternatenames, double latitude,
            double longitude, char featureclass, String featurecode, String countrycode,
            String cc2, String admin1code,
            String admin2code, String admin3code, String admin4code, Long population, int elevation, int dem, String tz,
            LocalDate modificationdate) {
        this.id = id;
        this.name = name;
        this.asciinames = asciinames;
        this.alternatenames = alternatenames;
        this.latitude = latitude;
        this.longitude = longitude;
        this.featureclass = featureclass;
        this.featurecode = featurecode;
        this.countrycode = countrycode;
        this.cc2 = cc2;
        this.admin1code = admin1code;
        this.admin2code = admin2code;
        this.admin3code = admin3code;
        this.admin4code = admin4code;
        this.population = population;
        this.elevation = elevation;
        this.dem = dem;
        this.tz = tz;
        this.modificationdate = modificationdate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAsciinames() {
        return asciinames;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public char getFeatureclass() {
        return featureclass;
    }

    public String getFeaturecode() {
        return featurecode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public String getCc2() {
        return cc2;
    }

    public String getAdmin1code() {
        return admin1code;
    }

    public String getAdmin2code() {
        return admin2code;
    }

    public String getAdmin3code() {
        return admin3code;
    }

    public String getAdmin4code() {
        return admin4code;
    }

    public Long getPopulation() {
        return population;
    }

    public double getElevation() {
        return elevation;
    }

    public int getDem() {
        return dem;
    }

    public String getTz() {
        return tz;
    }

    public LocalDate getModificationdate() {
        return modificationdate;
    }

}
