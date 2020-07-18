package com.example.ryan.workoutlog.Application.Domain;

public class UserProfile {
    private String firstName;
    private String lastName;
    private int age;
    private double weight;
    private String gender;
    public UserProfile(String firstName, String lastName,int age,double weight, String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.weight=weight;
        this.gender=gender;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
