package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class TryCounter {
    private int failedAttempts;
    private TimeoutDB timeoutDB;
    private Map<String, Integer> userFailedAttempts;

    public TryCounter(TimeoutDB timeoutDB) {
        this.failedAttempts = 0;
        this.timeoutDB = timeoutDB;
        this.userFailedAttempts = new HashMap<>();
    }

    public int getFailedAttempts(String userID) {
        return this.userFailedAttempts.getOrDefault(userID, 0);
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public TryCounter() {
        this.userFailedAttempts = new HashMap<>();
    }

    public void incrementFailedAttempts(String userID) {
        int currentFailedAttempts = getFailedAttempts(userID);
        this.userFailedAttempts.put(userID, currentFailedAttempts + 1);
        System.out.println("Current failed attempts for user " + userID + ": " + getFailedAttempts(userID));
        if  (getFailedAttempts(userID) >= 3) {
            System.out.println("User " + userID + " has reached the maximum number of failed attempts. Pushing to timeout.");
            pushToTimeout(userID);
        }
    }

    public void resetFailedAttempts() {
        this.failedAttempts = 0;
    }

    public void pushToTimeout(String userID) {
        timeoutDB.addTimeout(userID);
    }

    public void unlockDoor() {
        resetFailedAttempts();
    }

    public void displayFailedAttempts() {
        System.out.println("Failed attempts: " + this.failedAttempts);
    }
}
