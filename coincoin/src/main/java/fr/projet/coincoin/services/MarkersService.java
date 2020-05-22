package fr.projet.coincoin.services;

import fr.projet.coincoin.models.Marker;

import java.util.List;

public interface MarkersService {

    // CRUD : Create
    Marker save(Marker markerToAdd);

    // CRUD : Read
    List<Marker> getAll();

    Marker getById(Integer markerId);

    // CRUD : Update
    void update(Marker markerToUpdate);

    // CRUD : Delete
    void delete(Marker markerToDelete);

    // Fonction utiliser dans le controller
    List<Marker> getByLatLng(Double lat,Double lng);

    // Fonction utiliser dans le controller
    List<Marker> getByLat(Double lat);

    // Fonction utiliser dans le controller
    List<Marker> listAllMarkersBetweenLatLng(Double latmin, Double latmax ,Double lngmin, Double lngmax);
}
