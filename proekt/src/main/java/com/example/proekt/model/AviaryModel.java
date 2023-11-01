package com.example.proekt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "aviary")
public class AviaryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Длина названия должна быть не больше 30 символов")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Name is required")
    @Size(max = 10, message = "Длина кода должна быть не больше 10 символов")
    @Column(name = "code")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Длина описания должна быть не больше 200 символов")
    @Column(name = "description")
    private String description;

    public AviaryModel() {
    }

    public AviaryModel(long id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}