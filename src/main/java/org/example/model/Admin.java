package org.example.model;

public class Admin {
    private String adminID = "SmartLockAdmin";
    private String adminPassword = "admin123";

    public String getAdminID() {
        return this.adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminPassword() {
        return this.adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin() {
    }

    public Admin(String adminID, String adminPassword) {
        this.adminID = adminID;
        this.adminPassword = adminPassword;
    }

    public boolean validateCredentials(String adminID, String adminPassword) {
        return this.adminID.equals(adminID) && this.adminPassword.equals(adminPassword);
    }
}
