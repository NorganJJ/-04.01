package com.example.proekt.repo;

import com.example.proekt.model.AviaryModel;
import com.example.proekt.model.EventModel;
import com.example.proekt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface UserRepo extends JpaRepository<UserModel, Long> {
    java.lang.Iterable<UserModel> findByNameContainingIgnoreCase(String surname);

    UserModel findByEmail(String email);

}
