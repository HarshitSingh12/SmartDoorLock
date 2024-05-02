package org.example.util;

import java.util.Objects;

import org.example.model.Attempt;
import org.example.model.TryCounter;
import org.example.model.User;
import org.example.model.UsersDB;

public class FingerprintValidator {

    private static UsersDB usersDB;
    private static Attempt attempt;
    private static TryCounter tryCounter;
    public FingerprintValidator(UsersDB usersDB, Attempt attempt, TryCounter tryCounter) {
        this.usersDB = usersDB;
        this.attempt = attempt;
        this.tryCounter = tryCounter;
    }

    public static boolean authenticateFingerprint(String userID, String receivedFingerprintData, UsersDB usersDB1) {
        for (User user : usersDB1.getUsers()) {
            if (Objects.equals(user.getUserID(), userID)) {
                if (Objects.equals(user.getFingerPrintData(), receivedFingerprintData)) {
                    System.out.println("Fingerprint authentication successful for UserID: " + userID);
                    return true;
                }
                else if(receivedFingerprintData.equals("Fingerprint sensor is dirty")){
                    System.out.println("Please clean the fingerprint sensor and try again.");
                    return false;
                }
                else if(receivedFingerprintData.equals("Finger not placed properly")){
                    System.out.println("Please place your finger properly on the sensor.");
                    return false;
                }
                else if(receivedFingerprintData.equals("Empty")){
//                    System.out.println("No input provided");
                    return false;
                }
                else {
                    tryCounter.incrementFailedAttempts(userID);
                    System.out.println("Fingerprint authentication failed for UserID: " + userID);
                    return false;
                }
            }
        }

        System.out.println("This is coming from the FingerprintValidator class");
        System.out.println("User with UserID " + userID + " not found.");
        return false;
    }
}
