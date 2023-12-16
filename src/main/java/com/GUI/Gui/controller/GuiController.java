package com.GUI.Gui.controller;

import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.GUI.Gui.controller.PagesController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/functions")
public class GuiController {
    private PagesController pagesController;

    public GuiController(PagesController pagesController) {
        this.pagesController = pagesController;
    }

    @GetMapping
    public List<List<Double>> getFunctions() {
        List<List<Double>> functions = new ArrayList<>();
        functions.add(getTableValues(pagesController.getFirstFunction()));
        functions.add(getTableValues(pagesController.getSecondFunction()));
        functions.add(getTableValues(pagesController.getThirdFunction()));
        functions.add(getTableValues(pagesController.getDifferFirstFunction()));
        functions.add(getTableValues(pagesController.getDifferResFunction()));
        return functions;
    }

    private List<Double> getTableValues(TabulatedFunction function) {
        List<Double> tableValues = new ArrayList<>();
        for (int i = 0; i < function.getCount(); i++) {
            tableValues.add(function.getX(i));
            tableValues.add(function.getY(i));
        }
        return tableValues;
    }

}