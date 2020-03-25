package kr.co.fastcampus.eatgos.application;

public class PasswordWrongException extends RuntimeException {
    PasswordWrongException(){
        super("Password is wrong");
    }
}
