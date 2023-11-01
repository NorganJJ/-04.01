package com.example.proekt.repo;

import com.example.proekt.model.AnimalModel;
import com.example.proekt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepo extends JpaRepository<AnimalModel, Long> {
    java.lang.Iterable<AnimalModel> findByNameContainingIgnoreCase(String name);
}
