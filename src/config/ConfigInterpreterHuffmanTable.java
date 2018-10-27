package config;


import log.Log;

import java.io.*;
import java.util.Map;

public class ConfigInterpreterHuffmanTable extends ConfigInterpreterBase<Byte, String> {
    static String DELIMITER = "=";

    protected ConfigInterpreterHuffmanTable(String fileName) {
        super(fileName);
    }

    @Override
    protected void addConfiguration(Map<Byte, String> configMap, String key, String value) {
        try {
           /* if (!(key.length() == 1)) {
                throw new IllegalArgumentException();
            }*/
            Byte charKey = Byte.parseByte(key);
            configMap.put(charKey, value);
        } catch (IllegalArgumentException e) {
            Log.logReport("Unexpected key in table config file: " + fileName);
        }
    }

    @Override
    public void readConfiguration(Map<Byte, String> configMap) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
             BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fileName))) {
            String line;
            int c;
            byte b;
            while ((c = stream.read()) != -1 && ((line = reader.readLine()) != null)) {
                b = (byte)c;
                String[] set = line.split(DELIMITER);
                if (set.length != 2) {
                    Log.logReport("Invalid configuration syntax in file: " + fileName);
                }
                stream.skip(line.length()+1);
               // System.out.println("0");
                addConfiguration(configMap, String.valueOf(b), set[1]);
            }
        } catch (IOException e) {
            Log.logReport("Error while reading config file: " + fileName);
        }
        catch (IndexOutOfBoundsException e){
            Log.logReport("IndexOut");
        }
    }
}
