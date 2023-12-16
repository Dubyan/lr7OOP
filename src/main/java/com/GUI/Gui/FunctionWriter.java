package com.GUI.Gui;

import functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FunctionWriter {
    public static void writeFunctionToFile(TabulatedFunction function, String filePath) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Записываем значения точек функции в файл
            for (int i = 0; i < function.getCount(); i++) {
                bufferedWriter.write(function.getX(i) + "," + function.getY(i));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
