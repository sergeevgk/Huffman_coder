import java.io.*;

import config.Options;
import config.OptionsReader;
import huffman.HuffmanAlgorithm;
import log.Log;

public class HuffmanCoder {
    public static void main(String[] args){
        if (args[0] != null) {
            Log.init();
            Log.logReport("Program started.");
            String fileName = args[0];
            try {
                OptionsReader optionsReader = new OptionsReader(fileName);
                Options options = optionsReader.readOptions();
                HuffmanAlgorithm algo = new HuffmanAlgorithm(options);

                MyFileReader fileReader = new MyFileReader(options);
                MyFileWriter fileWriter = new MyFileWriter(options);
                char[] buffer;
                while ((buffer = fileReader.readInputFile()) != null) {
                    fileWriter.writeOutputFile(algo.startProcess(buffer));
                }
                fileWriter.close();
                fileReader.close();
            } catch (IOException e) {
                if (e.getMessage().equals("Missing configuration file name."))
                    Log.logReport(e.getMessage());
                else {
                    Log.logReport("Open file error.");
                }
            }
            //log before exit to report about program's work
            Log.logReport("Program finished.");
            Log.close();
        } else {
            Log.logReport("Missing command arguments.");
            Log.close();
        }
    }


}
