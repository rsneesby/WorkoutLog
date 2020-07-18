package com.example.ryan.workoutlog.application.domain;

public class UserProfile {
    private String firstName;
    private String lastName;
    private int age;
    private double weight;
    private String gender;
    @SuppressWarnings("unused")
    public UserProfile(String firstName, String lastName, int age, double weight, String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.weight=weight;
        this.gender=gender;

    }

    @SuppressWarnings("unused")
    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("unused")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @SuppressWarnings("unused")
    public String getLastName() {
        return lastName;
    }

    @SuppressWarnings("unused")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @SuppressWarnings("unused")
    public int getAge() {
        return age;
    }

    @SuppressWarnings("unused")
    public void setAge(int age) {
        this.age = age;
    }

    @SuppressWarnings("unused")
    public double getWeight() {
        return weight;
    }

    @SuppressWarnings("unused")
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @SuppressWarnings("unused")
    public String getGender() {
        return gender;
    }

    @SuppressWarnings("unused")
    public void setGender(String gender) {
        this.gender = gender;
    }
}
