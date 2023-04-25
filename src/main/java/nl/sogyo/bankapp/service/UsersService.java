package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.UsersModel;
import nl.sogyo.bankapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserModel registerUser(String login, String password, String email) {
        if (login == null && password == null) {
            return null;
        } else {
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            usersRepository.save(usersModel);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authentication(String login, String password){
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
