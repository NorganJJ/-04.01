package com.example.proekt.repo;

import com.example.proekt.model.AviaryModel;
import com.example.proekt.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryModel, Long> {

    java.lang.Iterable<CategoryModel> findByNameContainingIgnoreCase(String name);

}
