package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class Attempt {
    private String userID;
    private int attemptID;
    private static int nextAttemptID = 1;
    private String authType;
    private String attemptStatus;
    private LocalDateTime attemptDateTime;
    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAttemptID() {
        return this.attemptID;
    }

    public void setAttemptID(int attemptID) {
        this.attemptID = attemptID;
    }

    public String getAuthType() {
        return this.authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAttemptStatus() {
        return this.attemptStatus;
    }

    public void setAttemptStatus(String attemptStatus) {
        this.attemptStatus = attemptStatus;
    }

    public LocalDateTime getAttemptDateTime() {
        return this.attemptDateTime;
    }

    public void setAttemptDateTime(LocalDateTime attemptDateTime) {
        this.attemptDateTime = attemptDateTime;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "userID='" + userID + '\'' +
                ", attemptID=" + attemptID +
                ", authType='" + authType + '\'' +
                ", attemptStatus='" + attemptStatus + '\'' +
                ", attemptDateTime=" + attemptDateTime +
                '}';
    }

    public Attempt() {
    }

    public Attempt(String userID, boolean result, String authType) {
        this.attemptID = nextAttemptID++;
        this.userID = userID;
        this.authType = authType;
        this.attemptStatus = result ? "Success" : "Failed";
        this.attemptDateTime = LocalDateTime.now();
    }
}
