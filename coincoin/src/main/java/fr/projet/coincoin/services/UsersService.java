package fr.projet.coincoin.services;

import fr.projet.coincoin.models.User;
import java.util.List;

public interface UsersService {

    // CRUD : Create
    User save(User userToAdd);

    // CRUD : Read
    List<User> getAll();
    User getById(Integer userId);

    // CRUD : Update
    void update(User userToUpdate);

    // CRUD : Delete
    void delete(User userToDelete);
}
