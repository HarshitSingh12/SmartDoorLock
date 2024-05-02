package org.example.model;

public class RFID {
    private String rfidData;

    public String getRfidData() {
        return this.rfidData;
    }

    public void setRfidData(String rfidData) {
        this.rfidData = rfidData;
    }

    public RFID() {
    }

    public RFID(String rfidData) {
        this.rfidData = rfidData;
    }
}
