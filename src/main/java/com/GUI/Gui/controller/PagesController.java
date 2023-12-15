package com.GUI.Gui.controller;

import functions.*;
import functions.ArrayTabulatedFunction;
import functions.MathFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import operations.*;

import java.lang.reflect.Array;
import java.util.List;

@Controller
@Getter
public class PagesController {
    private final ArrayTabulatedFunctionFactory createFunctions;
    private int count = 3;
    private double xValues[] = {0, 0, 0};
    private double yValues[] = {0, 0, 0};
    private double xValuesd[] = {0, 0, 0};
    private double yValuesd[] = {0, 0, 0};
    private TabulatedFunction firstFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    private TabulatedFunction secondFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    private TabulatedFunction thirdFunction = ArrayTabulatedFunction.builder().xValues(xValuesd).yValues(yValuesd).build();
    TabulatedFunctionOperationService operations = new TabulatedFunctionOperationService();


    public PagesController(ArrayTabulatedFunctionFactory createFunctions) {
        this.createFunctions = createFunctions;
        operations.setFactory(createFunctions);
    }

    @PostMapping("/ArrayTabulatedFunctionFactory/create")
    public String createFunction(double[] xValues, double[] yValues)
    {
        firstFunction = createFunctions.create(xValues, yValues);
        return "redirect:/";
    }
    @PostMapping("/ByMathFunction/create")
    public String createByMathFunction(String Source, double xFrom, double xTo, int count) {
        MathFunction mathFunction = switch (Source) {
            case "Unit" -> new UnitFunction();
            case "Sqr" -> new SqrFunction();
            case "Zero" -> new ZeroFunction();
            case "Tan" -> new TanFunction();
            case "Sin" -> new SinFunction();
            case "Indentity" -> new IdentityFunction();
            default -> throw new IllegalArgumentException("Invalid Source value");
        };
        firstFunction = new ArrayTabulatedFunction(mathFunction, xFrom, xTo, count);
        return "redirect:/";
    }

    @PostMapping("/SecondArrayTabulatedFunctionFactory/create")
    public String createSecondFunction(double[] xValues, double[] yValues)
    {
        secondFunction = createFunctions.create(xValues, yValues);
        return "redirect:/";
    }
    @PostMapping("/SecondByMathFunction/create")
    public String createSecondByMathFunction(String Source, double xFrom, double xTo, int count) {
        MathFunction mathFunction = switch (Source) {
            case "Unit" -> new UnitFunction();
            case "Sqr" -> new SqrFunction();
            case "Zero" -> new ZeroFunction();
            case "Tan" -> new TanFunction();
            case "Sin" -> new SinFunction();
            case "Indentity" -> new IdentityFunction();
            default -> throw new IllegalArgumentException("Invalid Source value");
        };
        secondFunction = new ArrayTabulatedFunction(mathFunction, xFrom, xTo, count);
        return "redirect:/";
    }
    @GetMapping("/functions/firstFunction")
    public TabulatedFunction getFirstFunction() {
        return firstFunction;
    }

    @GetMapping("/functions/secondFunction")
    public TabulatedFunction getSecondFunction() {
        return secondFunction;
    }
    @GetMapping("/functions/thirdFunction")
    public TabulatedFunction getThirdFunction() {
        return thirdFunction;
    }

    @PostMapping("/operations/add")
    public String addFunction()
    {
        thirdFunction = operations.add(firstFunction, secondFunction);
        return "redirect:/";
    }
    @PostMapping("/operations/division")
    public String divisionFunction()
    {
        thirdFunction = operations.division(firstFunction, secondFunction);
        return "redirect:/";
    }
    @PostMapping("/operations/multiplication")
    public String multiplicationFunction()
    {
        thirdFunction = operations.multiplication(firstFunction, secondFunction);
        return "redirect:/";
    }
    @PostMapping("/operations/subtract")
    public String subtractFunction()
    {
        thirdFunction = operations.subtract(firstFunction, secondFunction);
        return "redirect:/";
    }
}
