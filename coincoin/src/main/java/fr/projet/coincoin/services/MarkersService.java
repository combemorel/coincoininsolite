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

    // Récupère les marqueurs par une latitude et longitude fournie
    List<Marker> getByLatLng(double lat,double lng);

    // Récupère les marqueurs qui sont situé entre les latitudes et longitudes fournies
    List<Marker> listAllMarkersBetweenLatLng(double latmin, double latmax ,double lngmin, double lngmax);
}
