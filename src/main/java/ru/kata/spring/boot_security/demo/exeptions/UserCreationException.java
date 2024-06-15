package ru.kata.spring.boot_security.demo.exeptions;

import javax.persistence.PersistenceException;

public class UserCreationException extends RuntimeException {
    public UserCreationException(String message, PersistenceException e) {
        super(message);
    }
}
