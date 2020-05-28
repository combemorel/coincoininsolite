package fr.projet.coincoin.services.impl;

import fr.projet.coincoin.models.User;
import fr.projet.coincoin.repositories.UsersRepository;
import fr.projet.coincoin.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional // Pour éviter une TransactionRequiredException
    public User save(User userToAdd) {
        return this.usersRepository.save(userToAdd);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.usersRepository.findAll();
    }

    @Override
    public User getById(Integer userId) {
        return this.usersRepository.findById(userId).orElseGet(null);
    }

    @Override
    @Transactional // Pour éviter une TransactionRequiredException
    public void update(User userToUpdate) {
        if (this.getById(userToUpdate.getId()) != null) {
            this.usersRepository.save(userToUpdate);
        }
    }

    @Override
    @Transactional // Pour éviter une TransactionRequiredException
    public void delete(User userToDelete) {
        if (this.getById(userToDelete.getId()) != null) {
            this.usersRepository.delete(userToDelete);
        }
    }
}
