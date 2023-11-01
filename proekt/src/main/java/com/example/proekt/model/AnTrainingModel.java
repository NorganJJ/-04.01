package com.example.proekt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "antraining")
public class AnTrainingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(0)
    @Column(name = "kolvo")
    private int kolvo;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина должна быть не больше 30 символов")
    @Column(name = "month")
    private String month;

    @ManyToOne
    @JoinColumn(name = "animal_id") // Указывает на столбец, который связывает сущности
    private AnimalModel animal;

    @ManyToOne
    @JoinColumn(name = "coach_id") // Указывает на столбец, который связывает сущности
    private CoachesModel coaches;

    public AnTrainingModel() {
    }

    public AnTrainingModel(long id, int kolvo, String month, AnimalModel animal, CoachesModel coaches) {
        this.id = id;
        this.kolvo = kolvo;
        this.month = month;
        this.animal = animal;
        this.coaches = coaches;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getKolvo() {
        return kolvo;
    }

    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public AnimalModel getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
    }

    public CoachesModel getCoaches() {
        return coaches;
    }

    public void setCoaches(CoachesModel coaches) {
        this.coaches = coaches;
    }
}
