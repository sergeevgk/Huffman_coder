package log;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    //make singleton for only 1 logger in program
    private static Log instance;

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
    private Log(){

    }
    private static FileWriter writer;

    public static void init() {
        try {
            writer = new FileWriter("log.log");//mb put into config file
        } catch (IOException e) {
            System.out.println("Cannot create or open log file.");
            e.printStackTrace();
        }
    }

    public static void logReport(String s) {
        try {
            writer.write(s + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Cannot write to log file.");
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot close log file");
            e.printStackTrace();
        }

    }
}
