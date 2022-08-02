package com.example.inventorymanagementapp;

public class Modal {
    private String name;
    private String name2,name3;

    public Modal(String name, String name2, String name3) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }
}
