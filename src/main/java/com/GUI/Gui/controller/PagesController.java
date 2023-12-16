package com.GUI.Gui.controller;

import com.GUI.Gui.FunctionReader;
import com.GUI.Gui.FunctionWriter;
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
import java.util.Arrays;
import java.util.List;

@Controller
@Getter
public class PagesController {
    private final ArrayTabulatedFunctionFactory createFunctions;
    private TabulatedDifferentialOperator differentialOperations;;
    private int count = 3;
    private double xValues[] = {0, 0, 0};
    private double yValues[] = {0, 0, 0};
    private TabulatedFunction firstFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    private TabulatedFunction secondFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    private TabulatedFunction thirdFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    private TabulatedFunction differFirstFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    private TabulatedFunction differResFunction = ArrayTabulatedFunction.builder().xValues(xValues).yValues(yValues).build();
    TabulatedFunctionOperationService operations = new TabulatedFunctionOperationService();


    public PagesController(ArrayTabulatedFunctionFactory createFunctions) {
        this.createFunctions = createFunctions;
        differentialOperations = new TabulatedDifferentialOperator(createFunctions);
        operations.setFactory(createFunctions);
    }

    public String checkArrays(double[] xVales, double[] secXValues, String Operation){
        if(xValues != secXValues)
            return "error";
        return Operation;
    }
    @PostMapping("/ArrayTabulatedFunctionFactory/create")
    public String createFunction(double[] xValues, double[] yValues)
    {
        for (int i = 0; i < xValues.length - 1; i++) {
            for (int j = i + 1; j < xValues.length; j++) {
                if (xValues[i] >= xValues[j]) {
                    return "error";
                }
            }
        }
        firstFunction = createFunctions.create(xValues, yValues);
        return "redirect:/";
    }
    @PostMapping("/ArrayTabulatedFunctionFactory/create/differ")
    public String createDifferFunction(double[] xValues, double[] yValues)
    {
        for (int i = 0; i < xValues.length - 1; i++) {
            for (int j = i + 1; j < xValues.length; j++) {
                if (xValues[i] >= xValues[j]) {
                    return "error";
                }
            }
        }
        differFirstFunction = createFunctions.create(xValues, yValues);
        return "redirect:/Differential.html";
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
    @PostMapping("/ByMathFunction/create/differ")
    public String createDifferByMathFunction(String Source, double xFrom, double xTo, int count) {
        MathFunction mathFunction = switch (Source) {
            case "Unit" -> new UnitFunction();
            case "Sqr" -> new SqrFunction();
            case "Zero" -> new ZeroFunction();
            case "Tan" -> new TanFunction();
            case "Sin" -> new SinFunction();
            case "Indentity" -> new IdentityFunction();
            default -> throw new IllegalArgumentException("Invalid Source value");
        };
        differFirstFunction = new ArrayTabulatedFunction(mathFunction, xFrom, xTo, count);
        return "redirect:/Differential.html";
    }

    @PostMapping("/SecondArrayTabulatedFunctionFactory/create")
    public String createSecondFunction(double[] xValues, double[] yValues){
        for (int i = 0; i < xValues.length - 1; i++) {
            for (int j = i + 1; j < xValues.length; j++) {
                if (xValues[i] >= xValues[j]) {
                    return "error";
                }
            }
        }
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
        secondFunction = firstFunction;
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
    @PostMapping("/operations/differential")
    public String differentialFunction()
    {
        differResFunction = differentialOperations.derive(differFirstFunction);
        return "redirect:/Differential.html";
    }
    @GetMapping("/error")
    public String error() {
        return "error.html";
    }
}
