package com.example.proekt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    @Min(0)
    @Column(name = "age", nullable = true)
    private int age;

    private boolean active;

    private String password;

    @Size(max = 30, message = "Длина фамилии должна быть не больше 30 символов")
    @Column(name = "surname")
    private String surname;

    @Size(max = 30, message = "Длина отчества должна быть не больше 30 символов")
    @Column(name = "second_name")
    private String second_name;

    @Size(max = 30, message = "Длина имя должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "user")
    private Collection<TicketModel> tickets;

    @ManyToMany
    @JoinTable (name="eventmark",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="event_id"))
    private List<EventModel> event;

    @ElementCollection(targetClass = roleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<roleEnum> roles;

    public UserModel() {
    }

    public UserModel(long id, String email, int age, boolean active, String password, String surname, String second_name, String name, Collection<TicketModel> tickets, List<EventModel> event, Set<roleEnum> roles) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.active = active;
        this.password = password;
        this.surname = surname;
        this.second_name = second_name;
        this.name = name;
        this.tickets = tickets;
        this.event = event;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Collection<TicketModel> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketModel> tickets) {
        this.tickets = tickets;
    }

    public List<EventModel> getEvent() {
        return event;
    }

    public void setEvent(List<EventModel> event) {
        this.event = event;
    }

    public Set<roleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<roleEnum> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
