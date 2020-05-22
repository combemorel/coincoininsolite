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
            value = "findId/{id}",
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
            value = "findLatLng/{lat}/{lng}",
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


    @GetMapping(
            value = "add/{title}/{resume}/{img}/{lat}/{lng}",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<Marker> add(
            @PathVariable("title") String title,
            @PathVariable("resume") String resume,
            @PathVariable("img") String img,
            @PathVariable("lat") Double lat,
            @PathVariable("lng") Double lng
        ) {
        String imgCheck = img.replace('_','/');
        Marker newMarker = new Marker();
        newMarker.setTitle(title);
        newMarker.setResume(resume);
        newMarker.setImg(imgCheck);
        newMarker.setLat(lat);
        newMarker.setLng(lng);
        try {

            return new ResponseEntity<Marker>( markersService.save(newMarker)
                    , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}