package org.example.util;

import org.example.model.Attempt;
import org.example.model.TryCounter;
import org.example.model.User;
import org.example.model.UsersDB;

public class RFIDValidator {

    private static UsersDB usersDB;
    private static Attempt attempt;
    private static TryCounter tryCounter;

    public RFIDValidator(UsersDB usersDB, Attempt attempt, TryCounter tryCounter) {
        this.usersDB = usersDB;
        this.attempt = attempt;
        this.tryCounter = tryCounter;
    }
    public static boolean authenticateRFID(String userID, String receivedRFIDData, UsersDB usersDB1) {
        for (User user : usersDB1.getUsers()) {
            if (user.getUserID().equals(userID)) {
                if(user.getRfidData().equals(receivedRFIDData)){
//                    System.out.println("RFID matched. Access granted.");
                    return true;
                }
                else if(receivedRFIDData.equals("RFID not placed properly")){
                    System.out.println("Please place the RFID properly and try again.");
                    return false;
                }
                else if(receivedRFIDData.equals("RFID is dirty"))
                {
                    System.out.println("Please clean the RFID and try again.");
                    return false;
                }
                else if(receivedRFIDData.equals("Empty")){
                    System.out.println("No input provided");
                    return false;
                }
                else{
                    tryCounter.incrementFailedAttempts(userID);
                    System.out.println("RFID authentication failed for UserID: " + userID);
                    return false;
                }
            }
        }
        System.out.println("This is coming from the RFIDValidator class");
        System.out.println("User with UserID " + userID + " not found.");
        return false;
    }
}
