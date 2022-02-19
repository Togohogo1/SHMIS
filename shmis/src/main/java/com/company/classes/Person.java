package com.company.classes;

import java.util.ArrayList;

// TODO regarrange getters

public abstract class Person {
    protected long age;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String id;
    protected String password;

    public Person() {}

    public Person(long age, String firstName, String lastName, String gender, String id, String password) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.id = id;
        this.password = password;
    }

    public long getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender.toUpperCase();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return "Person";
    }
}
