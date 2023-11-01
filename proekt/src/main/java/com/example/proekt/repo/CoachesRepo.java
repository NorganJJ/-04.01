package com.example.proekt.repo;

import com.example.proekt.model.CoachesModel;
import com.example.proekt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachesRepo extends JpaRepository<CoachesModel, Long> {
    java.lang.Iterable<CoachesModel> findByNameContainingIgnoreCase(String name);
}