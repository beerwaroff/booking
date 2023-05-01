package ru.beerwaroff.exceptions;

public class NoEntityException extends Exception {
    private final String message;
    public NoEntityException(Integer id) {
        message = "Пользователь с id: " + id + " не найден";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
