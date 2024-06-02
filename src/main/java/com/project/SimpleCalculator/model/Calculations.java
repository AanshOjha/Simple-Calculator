package com.project.SimpleCalculator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calculations")
public class Calculations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double a;
    private double b;
    private double result;
    private String operation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}