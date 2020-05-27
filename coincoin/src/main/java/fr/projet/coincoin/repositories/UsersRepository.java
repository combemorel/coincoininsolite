package fr.projet.coincoin.repositories;

import fr.projet.coincoin.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface qui étend de CrudRepository qui permet la création de méthodes
 *  - Create : save();
 *  - Read : getAll(); getById();
 *  - Update : update();
 *  - Delete : delete();
 */
public interface UsersRepository extends CrudRepository<User, Integer>  {
}
