package fr.projet.coincoin.controllers;

import fr.projet.coincoin.models.Marker;
import fr.projet.coincoin.models.User;
import fr.projet.coincoin.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping(
            value = "findAll",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<User>> findAll() {
        try {
            return new ResponseEntity<List<User>>(
                    usersService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "find/{id}",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<User>(
                    usersService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "add",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<User> add(@RequestBody User user ){
        try {
            return new ResponseEntity<User>( usersService.save(user)
                    , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "update",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE
    )
    public void udpdate(@RequestBody User user ){
        try {
            usersService.update(user);
        } catch (Exception e) {
        }
    }

    @DeleteMapping(
            value = "delete",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MediaType.ALL_VALUE
    )
    public void delete(@RequestBody User user ){
        try {
            usersService.delete(user);

        } catch (Exception e) {

        }
    }
}