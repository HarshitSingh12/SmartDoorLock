package org.example.model;

public class Fingerprint {
    private String fingerprintData;

    public String getFingerprintData() {
        return this.fingerprintData;
    }

    public void setFingerprintData(String fingerprintData) {
        this.fingerprintData = fingerprintData;
    }

    public Fingerprint() {
    }

    public Fingerprint(String fingerprintData) {
        this.fingerprintData = fingerprintData;
    }
}
