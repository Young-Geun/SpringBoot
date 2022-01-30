package choi.web.springboot.service;

import choi.web.springboot.domain.User;
import choi.web.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int registUser(User user) {
        if (userRepository.selectExistUser(user) == null) {
            return userRepository.registUser(user);
        } else {
            return -1;
        }
    }

    public int editUser(User user) {
        return userRepository.editUser(user);
    }

}
