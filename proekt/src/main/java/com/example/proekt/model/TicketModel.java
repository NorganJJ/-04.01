package com.example.proekt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ticket")
public class TicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;

    @Min(0)
    @Column(name = "cost")
    private int cost;

    @NotBlank(message = "Password is required")
    @Size(max = 30, message = "Длина должна быть не больше 30 символов")
    @Column(name = "time")
    private String time;

    @ManyToOne
    @JoinColumn(name = "user_id") // Указывает на столбец, который связывает сущности
    private UserModel user;

    public TicketModel() {
    }

    public TicketModel(long id, String name, int cost, String time, UserModel user) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.user = user;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
