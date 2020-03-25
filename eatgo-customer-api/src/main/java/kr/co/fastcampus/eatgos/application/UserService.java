package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.Restaurant;
import kr.co.fastcampus.eatgos.domain.User;
import kr.co.fastcampus.eatgos.domain.UserRepository;
import kr.co.fastcampus.eatgos.interfaces.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository =  userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String name, String password) {
      Optional<User> existed = userRepository.findByEmail(email);

      if(existed.isPresent()){
          throw new EmailExistedException(email);
      }
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1L)
                .build();

        return  userRepository.save(user);
    }



    public User authenticate(String email, String password) {
      //  User user = User.builder().email(mail).password(password).build();
        System.out.println("what");

        User user = userRepository.findByEmail(email).orElseThrow(()-> new EmailNotExistedException(email));
        System.out.println(user.getEmail());

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new PasswordWrongException();
        }
        return user;
    }
}
