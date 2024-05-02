package org.example.controller;

import org.example.model.*;
import org.example.service.ParallelAuthenticator;


import org.example.service.FaceAuthentication;
import org.example.service.FingerprintAuthentication;
import org.example.service.RFIDAuthentication;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DoorController {
    private RFIDAuthentication rfidAuthentication;
    private FaceAuthentication faceAuthentication;
    private FingerprintAuthentication fingerprintAuthentication;
    private Attempt attempt;
    private UsersDB usersDB;
    private Display display = new Display();
    private TryCounter tryCounter;
    private TimeoutDB timeoutDB;

    public DoorController(RFIDAuthentication rfidAuthentication, FaceAuthentication faceAuthentication, FingerprintAuthentication fingerprintAuthentication, UsersDB usersDB, Attempt attempt, TryCounter tryCounter, TimeoutDB timeoutDB) {
        this.rfidAuthentication = rfidAuthentication;
        this.faceAuthentication = faceAuthentication;
        this.fingerprintAuthentication = fingerprintAuthentication;
        this.usersDB = usersDB;
        this.attempt = attempt;
        this.tryCounter = tryCounter;
        this.timeoutDB = timeoutDB;
    }
    public void processUser(List<String> row) throws ExecutionException, InterruptedException {
        String userType = row.get(0);
        if (userType.equals("Admin")) {
            String adminID = row.get(1); // parse adminID as a String
            String adminPassword = row.get(2);
            Admin admin = new Admin();
            if (admin.validateCredentials(adminID, adminPassword)) {//
                String adminAction = row.get(3);
                switch (adminAction) {
                    case "addUser" -> {
                        String userID = row.get(4);
                        RFID rfidData = new RFID(row.get(5));
                        Face faceData = new Face(row.get(6));
                        Fingerprint fingerprintData = new Fingerprint(row.get(7));
                        User user = new User(userID, rfidData, faceData, fingerprintData);
                        usersDB.addUser(user);
                    }
                    case "modifyUser" -> {
                        String userID = row.get(4);
                        RFID rfidData = new RFID(row.get(5));
                        Face faceData = new Face(row.get(6));
                        Fingerprint fingerprintData = new Fingerprint(row.get(7));
                        User user = new User(userID, rfidData, faceData, fingerprintData);
                        usersDB.modifyUser(user);
                    }
                    case "deleteUser" -> {
                        String userID = row.get(4);
                        usersDB.deleteUser(userID);
                    }
                    case "viewUsers" -> usersDB.viewUsers();
                    default -> System.out.println("Invalid admin action");
                }
            }
            else {
                System.out.println("Invalid admin credentials");
            }
        } else if (userType.equals("User")) {
            if (timeoutDB.isTimeout(row.get(1))) {
                display.setDisplayData("User is locked out. Please try again later.");
            }
            else {
                String userID = row.get(1);
                RFID rfidData = new RFID(row.get(2));
                Face faceData = new Face(row.get(3));
                Fingerprint fingerprintData = new Fingerprint(row.get(4));
    //            System.out.println("Failed attempt number for userID " + userID + ": " + tryCounter.getFailedAttempts(userID));
                if (rfidAuthentication.authenticateRFID(userID, rfidData, usersDB)) {
                    ParallelAuthenticator parallelAuthenticator = new ParallelAuthenticator(faceAuthentication, fingerprintAuthentication, userID, faceData, fingerprintData, usersDB);
                    if (parallelAuthenticator.authenticate()) {
                        display.setDisplayData("Welcome, " + userID + "!");
                    } else {
                        display.setDisplayData("Access denied");
                    }
                }
            }
        }
    }
}
