package org.example.model;

public class Display {
    private String displayData;
    public String getDisplayData() {
        return this.displayData;
    }
    public Display() {
    }
    public Display(String display) {
        this.displayData = display;
    }
    public void setDisplayData(String display) {
        this.displayData = display;
        System.out.println(display);
    }
}
