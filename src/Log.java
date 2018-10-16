import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Log {
    //make singleton for only 1 logger in program
    private static volatile Log instance;

    private Log(){};

    public static Log getInstance(){
        if (instance == null)
            synchronized (Log.class){
            if (instance == null)
                instance = new Log();
            }
            return instance;
    }

    private static FileWriter writer;
    public static boolean init(){
        try {
            writer = new FileWriter("log.log");//mb put into config file
        }
        catch (IOException e){
            System.out.print("Cannot create or open log file.\n");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void logReport(String s){
        try {
            writer.write(s);
        }
        catch(IOException e){
            System.out.print("Cannot write to log file.\n");
            e.printStackTrace();
            return;
        }
        return;
    }
    public static void close() throws IOException{
        writer.flush();
        writer.close();
    }
}
