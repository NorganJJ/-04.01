package com.example.proekt.repo;

import com.example.proekt.model.AnTrainingModel;
import com.example.proekt.model.AnimalModel;
import com.example.proekt.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnTrainingRepo extends JpaRepository<AnTrainingModel, Long> {
    java.lang.Iterable<AnTrainingModel> findAllByMonthContainsIgnoreCase(String month);
}
