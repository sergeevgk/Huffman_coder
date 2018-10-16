import java.io.*;
import java.util.*;

//сделать класс интерпретатора - берет имя, читает конф. файл и
//читает грамматику однозначно зафиксир. в файле(num определяет, сколько лексем ожидать), массив для строковых литералов
//выяснить, правильно ли сделан конфигур. файл, правильный ли синтаксис (разделители), кол-во директив
//если интерпретатор отработал, то он отправляет алгоритму на вход контейнер с итогами разбора к.файла
//(итератор простого класса, напр.), чтобы можно было разобраться где что внутри лежит
//проверка: вход. файла, параметров, если всё ок, то пишет выходной файл
//минимум 2 класса - main, алгоритм и интерпретатор
public class Huffman_Coder {
    private static Huffman_Algorithm algo = new Huffman_Algorithm();

    public static void main(String[] args) throws IOException {
        if (args[0] != null) {
            Log.init();
            Log.logReport("Program started.\n");
            String fileName = args[0];
            try {
                algo.StartProcess(fileName);
            } catch (Exception e) {
                if (e.getMessage().equals("Missing configuration file name.\n"))
                    Log.logReport(e.getMessage());
                else {
                    Log.logReport("Open file error.\n");
                }
            }
            //log before exit to report about program's work
            Log.logReport("Program finished.\n");
            Log.close();
        } else {
            Log.logReport("Missing command arguments.\n");
            Log.close();
        }
    }


}
