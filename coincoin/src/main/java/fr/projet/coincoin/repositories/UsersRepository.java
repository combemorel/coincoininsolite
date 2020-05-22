package fr.projet.coincoin.repositories;

import fr.projet.coincoin.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepository extends CrudRepository<User, Integer>  {

}
