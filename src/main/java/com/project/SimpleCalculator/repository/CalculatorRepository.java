package com.project.SimpleCalculator.repository;

import com.project.SimpleCalculator.model.Calculations;
import org.springframework.data.repository.CrudRepository;

public interface CalculatorRepository extends CrudRepository<Calculations, Integer> {
    // all crud database methods
}
