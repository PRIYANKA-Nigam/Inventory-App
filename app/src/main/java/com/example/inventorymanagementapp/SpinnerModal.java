package com.example.inventorymanagementapp;

public class SpinnerModal {
    private String name,number;
    private int image1,image2;

    public SpinnerModal(String name, String number, int image1, int image2) {
        this.name = name;
        this.number = number;
        this.image1 = image1;
        this.image2 = image2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }
}
