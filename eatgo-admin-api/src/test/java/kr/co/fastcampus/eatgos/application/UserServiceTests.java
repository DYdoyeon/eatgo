package kr.co.fastcampus.eatgos.application;

import kr.co.fastcampus.eatgos.domain.User;
import kr.co.fastcampus.eatgos.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.initMocks(this);
       userService = new UserService(userRepository);
    }

    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<User>();

        mockUsers.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(100L)
                .build()
        );

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();
        User user = users.get(0);
        assertThat(user.getName(),is("tester"));
    }
    @Test
    public void addUser(){
        String email="admin@example.com";
        String name="Administrator";

        User mockUser = User.builder().name(name)
                .email(email).build();
        given(userRepository.save(any())).willReturn(mockUser);
        //given을 해야지만 mockUser가 들어갈 수 있음

        User user = userService.addUser(email,name);

        assertThat(user.getName(),is(name));
        //save했다는 것을 검증하는 것.

    }
    @Test
    public void updateUser(){
        Long id = 1004L;
        String email="admin@example.com";
        String name="Superman";
        Long level = 100L;

        User mockUser = User.builder().id(id)
                .name("Administrator")
                .email(email)
                .level(1L)
                .build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id,email,name,level) ;

        verify(userRepository).findById(eq(id));
        assertThat(user.getName(),is("Superman"));
        assertThat(user.isAdmin(),is(true));
    }

    @Test
    public void deactiveUser(){
        Long id = 1004L;

        User mockUser = User.builder()
                .id(id)
                .name("Administrator")
                .email("admin@example.com")
                .level(100L)
                .build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(1004L);
      //  userService.deactiveUser(1004L);
        verify(userRepository).findById(1004L);

       assertThat(user.isAdmin(),is(false));
        assertThat(user.isActive(),is(false));
    }
}