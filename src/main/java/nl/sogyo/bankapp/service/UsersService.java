package nl.sogyo.bankapp.service;

import nl.sogyo.bankapp.model.UsersModel;
import nl.sogyo.bankapp.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {
        if (login == null && password == null) {
            return null;
        } else {
            if (UsersRepository.findByLogin(login).isPresent()){
                System.out.println("The user already exists!");
                return null;
            }
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
