package org.example.util;

import java.util.Objects;

import org.example.model.Attempt;
import org.example.model.TryCounter;
import org.example.model.User;
import org.example.model.UsersDB;

public class FaceValidator {

    private static UsersDB usersDB;
    private static Attempt attempt;
    private static TryCounter tryCounter;
    public FaceValidator(UsersDB usersDB, Attempt attempt, TryCounter tryCounter) {
        this.usersDB = usersDB;
        this.attempt = attempt;
        this.tryCounter = tryCounter;
    }
    public static boolean authenticateFace(String userID, String receivedFaceData, UsersDB usersDB) {
        for (User user : usersDB.getUsers()) {
            if (Objects.equals(user.getUserID(), userID)) {
                if (Objects.equals(user.getFaceData(), receivedFaceData)) {
                    System.out.println("Face authentication successful for UserID: " + userID);
                    return true;
                }
                else if(receivedFaceData.equals("Object too far away")){
                    System.out.println("Please stand within the frame of the camera.");
                    return false;
                }
                else if(receivedFaceData.equals("Camera is dirty")){
                    System.out.println("Please clean the camera and try again.");
                    return false;
                }
                else if(receivedFaceData.equals("Empty")){
                    System.out.println("No input provided");
                    return false;
                }
                else {
                    tryCounter.incrementFailedAttempts(userID);
                    System.out.println("Face authentication failed for UserID: " + userID);
                    return false;
                }
            }
        }
        System.out.println("This is coming from the FaceValidator class");
        System.out.println("User with UserID " + userID + " not found.");
        return false;
    }
}
