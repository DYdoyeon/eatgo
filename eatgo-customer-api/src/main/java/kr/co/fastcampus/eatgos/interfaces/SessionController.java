package kr.co.fastcampus.eatgos.interfaces;


import kr.co.fastcampus.eatgos.application.UserService;
import kr.co.fastcampus.eatgos.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {
    @Autowired
    private UserService userService;


    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto resource) throws URISyntaxException {

        String url="/session";

        String email=resource.getEmail();
        String password = resource.getPassword();
        //String email = "tester@example.com";
        //String password = "test";
        User user = userService.authenticate(email,password);
        String accessToken = user.getAccessToken();


        return ResponseEntity.created(new URI(url))
                .body(SessionResponseDto.builder()
                        .accessToken(accessToken)
                        .build());
    }
}
