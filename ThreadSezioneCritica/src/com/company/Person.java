package com.company;

public class Person {
    private final String name, surname, code;
    private final int priority;

    public Person(String name, String surname, String code, int priority) {
        this.name = name;
        this.surname = surname;
        this.code = code;
        this.priority = priority;
    }

    public String[] getInfo(){
        return new String[]{name, surname, code};
    }

    public int getPriority() {
        return priority;
    }
}
