package kr.co.fastcampus.eatgos.application;

public class EmailNotExistedException extends RuntimeException {
    EmailNotExistedException(String email){
        super("Email is not registered: " + email);
    }
}
