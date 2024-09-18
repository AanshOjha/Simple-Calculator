package com.project.SimpleCalculator.service;

import com.project.SimpleCalculator.model.Calculations;
import com.project.SimpleCalculator.repository.CalculatorRepository;
import org.aspectj.weaver.tools.cache.CachedClassEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalculatorService {
    @Autowired
    private CalculatorRepository calculatorRepository;

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public List<Calculations> getAllCalculations() {
        return calculatorRepository.findAll();
    }

    public Calculations getById(int id) {
        return calculatorRepository.findById(id).orElse(null);
    }

    public Calculations saveToDb(Calculations calculations) {
        return calculatorRepository.save(calculations);
    }

    public Calculations deletebyId(int id) {
        var found = calculatorRepository.findById(id).orElse(null);
        calculatorRepository.deleteById(id);
        return found;
    }
}
