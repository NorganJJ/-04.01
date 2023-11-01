package com.example.proekt.repo;

import com.example.proekt.model.EventModel;
import com.example.proekt.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface EventRepo extends JpaRepository<EventModel, Long> {
    java.lang.Iterable<EventModel> findByNameContainingIgnoreCase(String name);
    EventModel findByName(String name);
}
