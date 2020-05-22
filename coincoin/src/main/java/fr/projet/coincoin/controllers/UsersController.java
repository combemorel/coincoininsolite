package fr.projet.coincoin.controllers;

import fr.projet.coincoin.models.Marker;
import fr.projet.coincoin.models.User;
import fr.projet.coincoin.services.MarkersService;
import fr.projet.coincoin.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    public List<User> getALL() {
        List<User> users = usersService.getAll();
        return users;
    }

    @GetMapping("/getById")
    public User getById(@RequestParam() Integer id) {
        User user = usersService.getById(id);
        return user;
    }

}