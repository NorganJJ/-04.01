package com.example.proekt.repo;

import com.example.proekt.model.TicketModel;
import com.example.proekt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<TicketModel, Long> {
    java.lang.Iterable<TicketModel> findByNameContainingIgnoreCase(String name);
}
