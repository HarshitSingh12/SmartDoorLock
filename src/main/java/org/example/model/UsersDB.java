package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsersDB {
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UsersDB() {
        this.users = new ArrayList<>();
    }

    public UsersDB(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
        System.out.println(user.getUserID() + " added to the database.");
    }

    public void modifyUser(User user) {
        for (User u : users) {
            if (Objects.equals(u.getUserID(), user.getUserID())) {
                u.setRfidData(user.getRfidData());
                u.setFaceData(user.getFaceData());
                u.setFingerPrintData(user.getFingerPrintData());
            }
        }
    }

    public void deleteUser(String userID) {
        users.removeIf(u -> userID.equals(u.getUserID()));
    }

    public void viewUsers() {
        for (User u : users) {
            System.out.println("User: " + u.getUserID() + " || RFID Data: " + u.getRfidData() + " || Face Data: " + u.getFaceData() + " || Fingerprint Data: " + u.getFingerPrintData());
        }
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public User getUser(String userID) {
        for (User user : users) {
            if (Objects.equals(user.getUserID(), userID)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByRfid(String rfidData) {
        for (User user : users) {
            if (Objects.equals(user.getRfidData(), rfidData)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByFingerPrint(String fingerPrintData) {
        for (User user : users) {
            if (Objects.equals(user.getFingerPrintData(), fingerPrintData)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByFace(String faceData) {
        for (User user : users) {
            if (Objects.equals(user.getFaceData(), faceData)) {
                return user;
            }
        }
        return null;
    }

    public boolean isUserExist(String userID) {
        for (User user : users) {
            if (Objects.equals(user.getUserID(), userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExistByRfid(String rfidData) {
        for (User user : users) {
            if (Objects.equals(user.getRfidData(), rfidData)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExistByFingerPrint(String fingerPrintData) {
        for (User user : users) {
            if (Objects.equals(user.getFingerPrintData(), fingerPrintData)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExistByFace(String faceData) {
        for (User user : users) {
            if (Objects.equals(user.getFaceData(), faceData)) {
                return true;
            }
        }
        return false;
    }
}
