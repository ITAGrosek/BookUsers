package com.feri.bookusers.model;

import java.time.LocalDate;

public class User {

    private String id; // Uporaba String za ID za enostavnost
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String username;
    private LocalDate registeredAt;

    public User() {
        // Praviloma potrebujemo prazen konstruktor
    }

    // Konstruktor z argumenti, če želite takoj nastaviti vse lastnosti
    public User(String name, String surname, Integer age, String email, String username) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.username = username;
        this.registeredAt = LocalDate.now(); // Nastavi trenutni datum kot datum registracije
    }

    // Getterji in setterji
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }

    // toString metoda za enostavno logiranje in debugiranje
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
