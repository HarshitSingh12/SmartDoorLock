package org.example.service;

import org.example.model.Attempt;
import org.example.model.Logger;
import org.example.model.TryCounter;
import org.example.model.UsersDB;
import org.example.util.FingerprintValidator;
import org.example.model.Logger;

public class FingerprintAuthentication {
    private FingerprintValidator fingerprintValidator;
    private Logger logger;

    public FingerprintValidator getFingerprintValidator() {
        return fingerprintValidator;
    }

    public void setFingerprintValidator(FingerprintValidator fingerprintValidator) {
        this.fingerprintValidator = fingerprintValidator;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public FingerprintAuthentication() {
    }
    public FingerprintAuthentication(UsersDB usersDB, Attempt attempt, TryCounter tryCounter, Logger logger) {
        this.logger = logger;
        this.fingerprintValidator = new FingerprintValidator(usersDB, attempt, tryCounter);
    }
    public boolean authenticateFingerprint(String userID, String receivedFingerprintData, UsersDB usersDB1) {
        boolean result = fingerprintValidator.authenticateFingerprint(userID, receivedFingerprintData, usersDB1);
        Attempt attempt = new Attempt(userID, result, "Fingerprint");
        logger.addAttempt(attempt);
        return result;
    }
}
