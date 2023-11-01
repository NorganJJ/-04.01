package com.example.proekt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
@Table(name = "coaches")
public class CoachesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Email is required")
    @Size(max = 30, message = "Длина названия должна быть не больше 30 символов")
    @Column(name = "email")
    private String email;

    @Min(0)
    @Column(name = "age")
    private int age;

    @NotBlank(message = "surname is required")
    @Size(max = 30, message = "Длина фамилии должна быть не больше 30 символов")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "second_name is required")
    @Size(max = 30, message = "Длина отчества должна быть не больше 30 символов")
    @Column(name = "second_name")
    private String second_name;

    @NotBlank(message = "name is required")
    @Size(max = 30, message = "Длина имя должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "coaches")
    private Collection<EventModel> coaches;

    @OneToMany (mappedBy = "coaches")
    private Collection<AnTrainingModel> antraining;

    public CoachesModel() {
    }

    public CoachesModel(long id, String email, int age, String surname, String second_name, String name, Collection<EventModel> coaches, Collection<AnTrainingModel> antraining) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.surname = surname;
        this.second_name = second_name;
        this.name = name;
        this.coaches = coaches;
        this.antraining = antraining;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<EventModel> getCoaches() {
        return coaches;
    }

    public void setCoaches(Collection<EventModel> coaches) {
        this.coaches = coaches;
    }

    public Collection<AnTrainingModel> getAntraining() {
        return antraining;
    }

    public void setAntraining(Collection<AnTrainingModel> antraining) {
        this.antraining = antraining;
    }
}