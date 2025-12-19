package com.doctor.base.application.GeoHash;

import java.util.List;

public interface GeoHashGeneratorPort {
    String generateGeoHash(double lats,double log,int precision);
    List<String> Neighbours(String experties, String geoHash);
}
