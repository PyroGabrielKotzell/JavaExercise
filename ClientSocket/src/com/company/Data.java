package com.company;

public class Data {
    private String id, name, surname, number;

    public Data(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
