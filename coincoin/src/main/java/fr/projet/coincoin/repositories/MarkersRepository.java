package fr.projet.coincoin.repositories;

import fr.projet.coincoin.models.Marker;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Interface qui étend de CrudRepository qui permet la création de méthodes
 *  - Create : save();
 *  - Read : getAll(); getById();
 *  - Update : update();
 *  - Delete : delete();
 */
public interface MarkersRepository extends CrudRepository<Marker, Integer>  {

    // Requête SQL
    // SELECT * FROM markers WHERE marker_latitude = lat AND marker_longitude = lng;
    List<Marker> findByLatAndLng(double lat, double lng);

    // Requête SQL
    // SELECT * FROM markers WHERE ( marker_latitude BETWEEN latmin AND latmax ) AND ( marker_longitude BETWEEN lngmin AND lngmax );
    List<Marker> findByLatBetweenAndLngBetween(double latmin,double latmax, double lngmin, double lngmax);
}
