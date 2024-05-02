package org.example.service;

import org.example.model.Face;
import org.example.model.Fingerprint;
import org.example.model.UsersDB;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelAuthenticator {
    private FaceAuthentication faceAuthentication;
    private FingerprintAuthentication fingerprintAuthentication;
    private String userID;
    private String faceData;
    private String fingerprintData;
    private UsersDB usersDB;

    public FaceAuthentication getFaceAuthentication() {
        return faceAuthentication;
    }

    public void setFaceAuthentication(FaceAuthentication faceAuthentication) {
        this.faceAuthentication = faceAuthentication;
    }

    public FingerprintAuthentication getFingerprintAuthentication() {
        return fingerprintAuthentication;
    }

    public void setFingerprintAuthentication(FingerprintAuthentication fingerprintAuthentication) {
        this.fingerprintAuthentication = fingerprintAuthentication;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    public String getFingerprintData() {
        return fingerprintData;
    }

    public void setFingerprintData(String fingerprintData) {
        this.fingerprintData = fingerprintData;
    }

    public UsersDB getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(UsersDB usersDB) {
        this.usersDB = usersDB;
    }

    public ParallelAuthenticator() {
    }

    public ParallelAuthenticator(FaceAuthentication faceAuthentication, FingerprintAuthentication fingerprintAuthentication, String userID, Face faceData, Fingerprint fingerprintData, UsersDB usersDB) {
        this.faceAuthentication = faceAuthentication;
        this.fingerprintAuthentication = fingerprintAuthentication;
        this.userID = userID;
        this.faceData = faceData.getFaceData();
        this.fingerprintData = fingerprintData.getFingerprintData();
        this.usersDB = usersDB;
    }

    public boolean authenticate() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Boolean> faceAuth = executor.submit(() -> faceAuthentication.authenticateFace(userID, faceData, usersDB));
        Future<Boolean> fingerprintAuth = executor.submit(() -> fingerprintAuthentication.authenticateFingerprint(userID, fingerprintData, usersDB));
        return faceAuth.get() || fingerprintAuth.get();
    }
}
