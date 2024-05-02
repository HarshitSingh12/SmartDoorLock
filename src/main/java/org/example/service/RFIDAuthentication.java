package org.example.service;

import org.example.model.Attempt;
import org.example.model.RFID;
import org.example.model.TryCounter;
import org.example.model.UsersDB;
import org.example.util.RFIDValidator;
import org.example.model.Logger;

public class RFIDAuthentication {
    private RFIDValidator rfidValidator;
    private Logger logger;

    public RFIDValidator getRfidValidator() {
        return rfidValidator;
    }

    public void setRfidValidator(RFIDValidator rfidValidator) {
        this.rfidValidator = rfidValidator;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public RFIDAuthentication() {
    }
    public RFIDAuthentication(UsersDB usersDB, Attempt attempt, TryCounter tryCounter, Logger logger) {
        this.logger = logger;
        this.rfidValidator = new RFIDValidator(usersDB, attempt, tryCounter);
    }
    public boolean authenticateRFID(String userID, RFID receivedRFIDData, UsersDB usersDB) {
        boolean result =  rfidValidator.authenticateRFID(userID, receivedRFIDData.getRfidData(), usersDB);
        Attempt attempt = new Attempt(userID, result, "RFID");
        logger.addAttempt(attempt);
        return result;
    }
}
