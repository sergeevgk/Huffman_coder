import java.io.*;

public class HuffmanCoder {
    public static void main(String[] args) throws IOException {
        if (args[0] != null) {
            Log.init();
            Log.logReport("Program started.\n");
            String fileName = args[0];
            try {
                OptionsReader optionsReader = new OptionsReader(fileName);
                HuffmanAlgorithm algo = new HuffmanAlgorithm(optionsReader.readOptions());
                algo.startProcess();
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
