package fr.projet.coincoin.controllers;

import fr.projet.coincoin.models.Marker;
import fr.projet.coincoin.models.User;
import fr.projet.coincoin.services.MarkersService;
import fr.projet.coincoin.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marker")
public class MarkersController {

    @Autowired
    private MarkersService markersService;

    @RequestMapping(
            value = "findAll",
            method = RequestMethod.GET,
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

    @RequestMapping(
            value = "findId/{id}",
            method = RequestMethod.GET,
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

    @RequestMapping(
            value = "findLatLng/{lat}/{lng}",
            method = RequestMethod.GET,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<Marker>> find(@PathVariable("lat") Double lat,@PathVariable("lng") Double lng) {
        try {
            return new ResponseEntity<List<Marker>>(
                    markersService.getByLatLng(lat,lng), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "findLat/{lat}",
            method = RequestMethod.GET,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<Marker>> find(@PathVariable("lat") Double lat) {
        try {
            return new ResponseEntity<List<Marker>>(
                    markersService.getByLat(lat), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(value = "/getByLng")
//    public List<Marker> getByLng(@RequestParam() Float lng) {
//        List<Marker> marker = markersService.getByLng(lng);
//        return marker;
//    }

    @GetMapping(value = "/add")
    public void addMarker( @RequestParam() String title, @RequestParam() String resume, @RequestParam() String img, @RequestParam() Double lat, @RequestParam() Double lon ) {

        Marker newMarker = new Marker();
        newMarker.setTitle(title);
        newMarker.setResume(resume);
        newMarker.setImg(img);
        newMarker.setLat(lat);
        newMarker.setLng(lon);

        try {
            markersService.save(newMarker);
        } catch (Exception e) {
            e.getMessage();
        }

    }
}