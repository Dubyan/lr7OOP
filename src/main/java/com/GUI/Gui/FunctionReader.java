package com.GUI.Gui;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FunctionReader {
    public static TabulatedFunction readFunctionFromFile(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            double[] xValues = null;
            double[] yValues = null;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (xValues == null) {
                    xValues = new double[values.length];
                    yValues = new double[values.length];
                }
                xValues[count] = Double.parseDouble(values[0]);
                yValues[count] = Double.parseDouble(values[1]);
                count++;
            }

            bufferedReader.close();

            return new ArrayTabulatedFunction(xValues, yValues);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
