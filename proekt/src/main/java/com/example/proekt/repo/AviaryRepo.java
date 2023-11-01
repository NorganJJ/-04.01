package com.example.proekt.repo;

import com.example.proekt.model.AviaryModel;
import com.example.proekt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AviaryRepo extends JpaRepository<AviaryModel, Long> {

    java.lang.Iterable<AviaryModel> findByNameContainingIgnoreCase(String code);

}
