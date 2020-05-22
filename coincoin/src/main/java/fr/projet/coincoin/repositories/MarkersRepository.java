package fr.projet.coincoin.repositories;

import fr.projet.coincoin.models.Marker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarkersRepository extends CrudRepository<Marker, Integer>  {

    // SELECT * FROM markers WHERE latitude = latitude AND longitude = longitude;
    List<Marker> findByLatAndLng(Double lat, Double lng);
    List<Marker> findByLat(Double lat);

    List<Marker> findByLatBetweenAndLngBetween(Double latmin,Double latmax, Double lngmin, Double lngmax);

}
