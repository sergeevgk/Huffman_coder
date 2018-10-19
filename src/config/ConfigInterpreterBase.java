package config;

import log.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public abstract class ConfigInterpreterBase<T, Q> implements ConfigInterpreter<T, Q> {
    protected String fileName;
    private final String DELIMITER = "=";


    protected ConfigInterpreterBase(String fileName) {
        this.fileName = fileName;
    }

    protected abstract void addConfiguration(Map<T, Q> configMap, String key, String value);

    @Override
    public void readConfiguration(Map<T, Q> configMap) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2) {
                    Log.logReport("Invalid configuration syntax in file: " + fileName);
                }
                addConfiguration(configMap, set[0], set[1]);
            }
        } catch (IOException e) {
            Log.logReport("Error while reading config file: " + fileName);
        }
    }
}
