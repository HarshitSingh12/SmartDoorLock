package org.example.service;

import org.example.model.Attempt;
import org.example.model.Logger;
import org.example.model.TryCounter;
import org.example.model.UsersDB;
import org.example.util.FaceValidator;
import org.example.model.Logger;

public class FaceAuthentication {
    private FaceValidator faceValidator;
    private Logger logger;

    public FaceValidator getFaceValidator() {
        return faceValidator;
    }

    public void setFaceValidator(FaceValidator faceValidator) {
        this.faceValidator = faceValidator;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public FaceAuthentication() {
    }

    public FaceAuthentication(UsersDB usersDB, Attempt attempt, TryCounter tryCounter, Logger logger) {
        this.logger = logger;
        this.faceValidator = new FaceValidator(usersDB, attempt, tryCounter);
    }
    public boolean authenticateFace(String userID, String receivedFaceData, UsersDB usersDB1) {
        boolean result = faceValidator.authenticateFace(userID, receivedFaceData, usersDB1);
        Attempt attempt = new Attempt(userID, result, "Face");
        logger.addAttempt(attempt);
        return result;
    }
}
