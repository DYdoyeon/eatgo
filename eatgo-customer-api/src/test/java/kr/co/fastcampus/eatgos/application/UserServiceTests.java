package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.User;
import kr.co.fastcampus.eatgos.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTests {


    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository,passwordEncoder);
    }

    @Test
    public void registerUser(){

        String email = "test@example.com";
        String name = "Tester";
        String password = "test";

        userService.registerUser(email,name, password);

        verify(userRepository).save(any());
    }

    @Test(expected = EmailExistedException.class)
    public void registerUserWithExistedEmail(){
        String email = "test@example.com";
        String name = "Tester";
        String password = "test";

        User user = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        userService.registerUser(email,name, password);

         //   verify(userRepository,never()).save(any());
    }
    @Test
    public void authenticateWithValidAttributes()
    {
        String email = "tester@example.com";
        String password = "test";

        User mockUser = User.builder()
                .email(email).build();

        given(userRepository.findByEmail(email)).
                willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any())).willReturn(true);

        User user = userService.authenticate(email,password);

        assertThat(user.getEmail(),is(email));
    }
    @Test(expected = EmailNotExistedException.class)
    public void authenticateWithNotValidAttributes()
    {
        String email = "x@example.com";
        String password = "test";

        User mockUser = User.builder()
                .email(email).build();

        given(userRepository.findByEmail(email))
                .willReturn(Optional.empty());

        given(passwordEncoder.matches(any(),any())).willReturn(true);

        User user =    userService.authenticate(email,password);
        assertThat(user.getEmail(),is(email));

    }

    @Test(expected = PasswordWrongException.class)
    public void authenticateWithWrongPassword()
    {
        String email = "tester@example.com";
        String password = "x";

        User mockUser = User.builder()
                .email(email).build();

        given(userRepository.findByEmail(email))
                .willReturn(Optional.of(mockUser));
        given(passwordEncoder.matches(any(),any())).willReturn(false);

        User user =  userService.authenticate(email,password);
        assertThat(user.getEmail(),is(email));
  }


}