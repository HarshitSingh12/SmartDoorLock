package org.example.model;

public class User {
    private String userID;
    private String RfidData;

    private String FingerPrintData;

    private String FaceData;

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRfidData() {
        return this.RfidData;
    }

    public void setRfidData(String rfidData) {
        this.RfidData = rfidData;
    }

    public String getFingerPrintData() {
        return this.FingerPrintData;
    }

    public void setFingerPrintData(String fingerPrintData) {
        this.FingerPrintData = fingerPrintData;
    }

    public String getFaceData() {
        return this.FaceData;
    }

    public void setFaceData(String faceData) {
        this.FaceData = faceData;
    }

    public User() {
    }

    public User(String userID, RFID rfid, Face face, Fingerprint fingerprint) {
        this.userID = userID;
        this.RfidData = rfid.getRfidData();
        this.FaceData = face.getFaceData();
        this.FingerPrintData = fingerprint.getFingerprintData();
    }

}
