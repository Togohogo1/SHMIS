package com.company.Classes;

public abstract class Person {
    protected long age;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String id;
    protected String password;

    public Person(long age, String firstName, String lastName, String gender, String id, String password) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.id = id;
        this.password = password;
    }
}
