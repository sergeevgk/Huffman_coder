package config;


import log.Log;

import java.util.Map;

public class ConfigInterpreterHuffmanTree extends ConfigInterpreterBase<Character, String> {

    protected ConfigInterpreterHuffmanTree(String fileName) {
        super(fileName);
    }

    @Override
    protected void addConfiguration(Map<Character, String> configMap, String key, String value) {
        try {
            if (!(key.length() == 1)) {
                throw new IllegalArgumentException();
            }
            Character charKey = key.charAt(0);
            configMap.put(charKey, value);
        } catch (IllegalArgumentException e) {
            Log.logReport("Unexpected key in table config file: " + fileName);
        }
    }
}
