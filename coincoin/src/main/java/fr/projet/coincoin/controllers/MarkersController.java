package fr.projet.coincoin.controllers;

import fr.projet.coincoin.models.Marker;
import fr.projet.coincoin.models.User;
import fr.projet.coincoin.services.MarkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marker")
public class MarkersController {

    @Autowired
    private MarkersService markersService;

    @GetMapping(
            value = "findAll",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<Marker>> findAll() {
        try {
            return new ResponseEntity<List<Marker>>(
                    markersService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "find/{id}",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
        )
    public ResponseEntity<Marker> find(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<Marker>(
                    markersService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "find/{lat}/{lng}",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<Marker>> find(@PathVariable("lat") double lat,@PathVariable("lng") double lng) {
        try {
            return new ResponseEntity<List<Marker>>(
                    markersService.getByLatLng(lat,lng), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "find/{latmin}/{lngmin}/{latmax}/{lngmax}",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<Marker>> find(@PathVariable("latmin") double latmin,@PathVariable("lngmin") double lngmin,@PathVariable("latmax") double latmax,@PathVariable("lngmax") double lngmax) {
        try {
            return new ResponseEntity<List<Marker>>(
                    markersService.listAllMarkersBetweenLatLng(latmin,latmax,lngmin,lngmax), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(
            value = "add",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE
    )
    public ResponseEntity<Marker> add(@RequestBody Marker marker ){
        try {
            return new ResponseEntity<Marker>( markersService.save(marker), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "update",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE
    )
    public void udpdate(@RequestBody Marker marker ){
        try {
            markersService.update(marker);
        } catch (Exception e) {

        }
    }

    @DeleteMapping(
            value = "delete",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE
    )
    public void delete(@RequestBody Marker marker ){
        try {
            markersService.delete(marker);
        } catch (Exception e) {

        }
    }
    
}