package com.example.proekt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;

@Entity
@Table(name = "animal")
public class AnimalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина названия должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;
    @Min(0)
    @Column(name = "age")
    private int age;

    @NotBlank(message = "Color is required")
    @Size(max = 30, message = "Длина описания должна быть не больше 30 символов")
    @Column(name = "color")
    private String color;

    @OneToMany (mappedBy = "animal", fetch = FetchType.EAGER)
    private Collection<AnTrainingModel> antraining;

    public AnimalModel() {
    }

    public AnimalModel(long id, String name, int age, String color) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
