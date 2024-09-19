package com.project.SimpleCalculator.controller;

import com.project.SimpleCalculator.model.Calculations;
import com.project.SimpleCalculator.repository.CalculatorRepository;
import com.project.SimpleCalculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService = new CalculatorService();

    private final CalculatorRepository calculatorRepository;

    @Autowired
    public CalculatorController(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    @GetMapping()
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody Calculations calculations) {
        String operation = calculations.getOperation();
        double a = calculations.getA();
        double b = calculations.getB();
        double result = switch (operation.toLowerCase()) {
            case "add" -> calculatorService.add(a, b);
            case "subtract" -> calculatorService.subtract(a, b);
            case "multiply" -> calculatorService.multiply(a, b);
            case "divide" -> calculatorService.divide(a, b);
            default -> throw new IllegalArgumentException("Invalid operation.");
        };
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getall")
    public List<Calculations> getAll() {
        return calculatorService.getAllCalculations();
    }

    @GetMapping("/get")
    public Calculations getById(@RequestParam int id) {
        return calculatorService.getById(id);
    }

    @DeleteMapping("/delete")
    public Calculations delete(@RequestParam int id) {
        return calculatorService.deletebyId(id);
    }

//    @PostMapping("/db")
//    public Calculations saveToDB(@RequestBody Calculations calculations) {
//        String operation = calculations.getOperation();
//        double a = calculations.getA();
//        double b = calculations.getB();
//        double result = switch (operation.toLowerCase()) {
//            case "add" -> calculatorService.add(a, b);
//            case "subtract" -> calculatorService.subtract(a, b);
//            case "multiply" -> calculatorService.multiply(a, b);
//            case "divide" -> calculatorService.divide(a, b);
//            default -> throw new IllegalArgumentException("Invalid operation.");
//        };
//
//
//        calculations.setId(new Random().nextInt(1, 100));
//        calculations.setA(a);
//        calculations.setB(b);
//        calculations.setResult(result);
//        calculations.setOperation(operation);
//
//        Calculations cal = calculatorRepository.save(calculations);
//        return calculatorService.saveToDb(calculations);
//    }
}