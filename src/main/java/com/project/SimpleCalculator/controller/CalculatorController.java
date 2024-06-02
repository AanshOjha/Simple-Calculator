package com.project.SimpleCalculator.controller;

import com.project.SimpleCalculator.model.Calculations;
import com.project.SimpleCalculator.repository.CalculatorRepository;
import com.project.SimpleCalculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping("/db")
    public ResponseEntity<String> saveToDB(@RequestBody Calculations calculations) {
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


        calculations.setId(new Random().nextInt(1, 100));
        calculations.setA(a);
        calculations.setB(b);
        calculations.setResult(result);
        calculations.setOperation(operation);

        Calculations cal = calculatorRepository.save(calculations);
        return ResponseEntity.created(URI.create("/calculator/" + cal.getId())).body(cal.toString());
    }

    @GetMapping("/getall")
    public @ResponseBody Iterable<Calculations> getAll() {
        return calculatorRepository.findAll();
    }

    @GetMapping("/get")
    public @ResponseBody Optional<Calculations> getById(@RequestParam int id) {
        return calculatorRepository.findById(id);
    }


//    @PutMapping("/add")
//    public ResponseEntity<String> calcuateWithPut(@RequestParam double a, @RequestParam double b) {
//        return ResponseEntity.ok("Result: " + calculatorService.add(a, b));
//    }
}
