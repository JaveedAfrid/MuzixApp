package com.learn.favouritesservices.exception;

public class TrackidWithEmailAlreadyExists extends RuntimeException {
    public TrackidWithEmailAlreadyExists(String message) {
        super(message);
    }
}
