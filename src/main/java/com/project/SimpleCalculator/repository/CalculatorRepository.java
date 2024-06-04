package com.project.SimpleCalculator.repository;

import com.project.SimpleCalculator.model.Calculations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CalculatorRepository extends JpaRepository<Calculations, Integer> {
    // all crud database methods
}
