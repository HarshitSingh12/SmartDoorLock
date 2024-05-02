package org.example.model;

public class Face {
    private String faceData;

    public String getFaceData() {
        return this.faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    public Face() {
    }

    public Face(String faceData) {
        this.faceData = faceData;
    }
}
