package fr.projet.coincoin.services.impl;

import fr.projet.coincoin.models.Marker;
import fr.projet.coincoin.repositories.MarkersRepository;
import fr.projet.coincoin.services.MarkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MarkersServiceImpl implements MarkersService {

    @Autowired
    private MarkersRepository markersRepository;

    @Override
    @Transactional // Pour éviter une TransactionRequiredException
    public Marker save(Marker markerToAdd) {
        return this.markersRepository.save(markerToAdd);
    }

    @Override
    public List<Marker> getAll() {
        return (List<Marker>) this.markersRepository.findAll();
    }

    @Override
    public Marker getById(Integer markerId) {
        return this.markersRepository.findById(markerId).orElseGet(null);
    }


    @Transactional // Pour éviter une TransactionRequiredException
    public void update(Marker markerToUpdate) {
        if (this.getById(markerToUpdate.getId()) != null) {
            this.markersRepository.save(markerToUpdate);
        }
    }

    @Override
    @Transactional // Pour éviter une TransactionRequiredException
    public void delete(Marker markerToDelete) {
        if (this.getById(markerToDelete.getId()) != null) {
            this.markersRepository.delete(markerToDelete);
        }
    }

    @Override
    public List<Marker> getByLatLng(double lat, double lng) {
        return this.markersRepository.findByLatAndLng(lat,lng);
    }

    @Override
    public List<Marker> listAllMarkersBetweenLatLng(double latmin, double latmax, double lngmin, double lngmax) {
        return this.markersRepository.findByLatBetweenAndLngBetween(latmin, latmax, lngmin, lngmax);
    }
}
