import java.io.*;

import config.Options;
import config.OptionsReader;
import log.Log;

public class HuffmanCoder {
    public static void main(String[] args) throws IOException {
        if (args[0] != null) {
            Log.init();
            Log.logReport("Program started.\n");
            String fileName = args[0];
            try {
                OptionsReader optionsReader = new OptionsReader(fileName);
                Options options = optionsReader.readOptions();
                HuffmanAlgorithm algo = new HuffmanAlgorithm(options);
                MyFileReader fileReader = new MyFileReader(options);
                MyFileWriter fileWriter = new MyFileWriter(options);
                char[] buffer;
                while ((buffer = fileReader.readInputFile()) != null) {
                    fileWriter.writeOutputFile(algo.startProcess(buffer, options));
                }
                fileWriter.close();
                fileReader.close();
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
