package com.doctor.base.application.GeoHash;

import ch.hsr.geohash.GeoHash;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.List;

@Singleton
 class GeoHashGeneratorAdaptor implements GeoHashGeneratorPort {

    @Override
    public   String generateGeoHash(double lats, double log,int precision) {
         return GeoHash.withCharacterPrecision(lats,log,precision).toBase32();
    }

    public List<String> Neighbours(String experties, String geoHash) {
        GeoHash center = GeoHash.fromGeohashString(geoHash);

        List<GeoHash> neighbours = Arrays.asList(
                center.getEasternNeighbour() ,
                center.getWesternNeighbour(),
                center.getNorthernNeighbour(),
                center.getSouthernNeighbour(),
                center.getNorthernNeighbour().getEasternNeighbour(),
                center.getNorthernNeighbour().getWesternNeighbour(),
                center.getSouthernNeighbour().getEasternNeighbour(),
                center.getSouthernNeighbour().getWesternNeighbour());


        return neighbours.stream().map(GeoHash::toBase32).toList();

    }
}
