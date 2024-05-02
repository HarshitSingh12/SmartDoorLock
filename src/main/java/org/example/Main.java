package org.example;

import org.example.controller.DoorController;
import org.example.model.*;
import org.example.service.FaceAuthentication;
import org.example.service.FingerprintAuthentication;
import org.example.service.RFIDAuthentication;
import org.example.util.CSVReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello and welcome!");
        UsersDB usersDB = new UsersDB();
        Attempt attempt = new Attempt();
        Logger logger = new Logger();
        TimeoutDB timeoutDB = new TimeoutDB();
        TryCounter tryCounter = new TryCounter(timeoutDB);
        RFIDAuthentication rfidAuthentication = new RFIDAuthentication(usersDB, attempt, tryCounter, logger);
        FaceAuthentication faceAuthentication = new FaceAuthentication(usersDB, attempt, tryCounter, logger);
        FingerprintAuthentication fingerprintAuthentication = new FingerprintAuthentication(usersDB, attempt, tryCounter, logger);
        DoorController doorController = new DoorController(rfidAuthentication, faceAuthentication, fingerprintAuthentication, usersDB, attempt, tryCounter, timeoutDB);
        try {
            Iterator<List<String>> rowIterator = CSVReader.readCSV("src/main/resources/UserGetsTimedOut.csv");
            while (rowIterator.hasNext()) {
                doorController.processUser(rowIterator.next());
            }
            System.out.println("End of CSV file reached. Terminating program.");
            System.exit(0);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}