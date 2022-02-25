package com.company.classes;

/**
 * General template class to create other types of people.
 */
public abstract class Person {
    protected long age;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String id;
    protected String password;

    /**
     * Default constructor
     */
    public Person() {}

    /**
     * Initialized a person from constructor parameters.
     *
     * @param age The person's age
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param gender The person's gender
     * @param id The person's ID
     * @param password The person's password
     */
    public Person(long age, String firstName, String lastName, String gender, String id, String password) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.id = id;
        this.password = password;
    }

    /**
     * Returns the person's password.
     *
     * @return the person's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the person's age.
     *
     * @return the person's age
     */
    public long getAge() {
        return age;
    }

    /**
     * Returns the person's first name.
     *
     * @return the person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the person's last name.
     *
     * @return the person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the person's gender.
     *
     * @return the person's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns the person's id.
     *
     * @return the person's id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the person's passwords to a new password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the person's age to a new age.
     *
     * @param age The new age.
     */
    public void setAge(long age) {
        this.age = age;
    }

    /**
     * Sets the person's first name to a new first name.
     *
     * @param firstName The new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the person's last name to a new last name.
     *
     * @param lastName The new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the person's gender to a new gender.
     *
     * @param gender The new gender
     */
    public void setGender(String gender) {
        this.gender = gender.toUpperCase();
    }

    /**
     * Sets the person's ID to a new ID.
     *
     * @param id the new ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the type of person.
     *
     * @return the type of person
     */
    public String getDesignation() {
        return "Person";
    }
}
