package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static int nextLogID = 1;
    private int logID;
    private List<Attempt> attempts;

    public int getLogID() {
        return this.logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public List<Attempt> getAttempts() {
        return this.attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    public Logger(int logID, List<Attempt> attempts) {
        this.logID = logID;
        this.attempts = attempts;
    }

    public Logger() {
        this.logID = nextLogID++;
        this.attempts = new ArrayList<>();
    }

    public void addAttempt(Attempt attempt) {
        this.attempts.add(attempt);
        System.out.println(attempt.toString());
    }
}
