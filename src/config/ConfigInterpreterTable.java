package config;


import log.Log;

import java.util.Map;

public class ConfigInterpreterTable extends ConfigInterpreterBase<Character, Integer> {

    protected ConfigInterpreterTable(String fileName) {
        super(fileName);
    }

    @Override
    protected void addConfiguration(Map<Character, Integer> configMap, String key, String value) {
        try {
            if (!(key.length() == 1)) {
                throw new IllegalArgumentException();
            }
            Character charKey = key.charAt(0);
            Integer intValue = Integer.parseInt(value);
            configMap.put(charKey, intValue);
        } catch (IllegalArgumentException e) {
            Log.logReport("Unexpected key in table config file: " + fileName);
        }
    }
}
