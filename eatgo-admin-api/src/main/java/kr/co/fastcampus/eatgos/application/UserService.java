package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.User;
import kr.co.fastcampus.eatgos.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserService
{


    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    public List<User> getUsers(){
        List<User> users = userRepository.findAll();


        return users;

    }

    public User addUser(String email, String name) {
        User newUser = User.builder().email(email).name(name).level(1L).build();
        User user = userRepository.save(newUser);

        return user;
    }

    public User updateUser(Long id, String email, String name, Long level) {
        User user = userRepository.findById(id).orElse(null);

        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);

        return user;


    }


    public User deactiveUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.deactivate();
        return user;
    }
}
