package config;

import log.Log;

import java.util.Map;

public class ConfigInterpreterOptions extends ConfigInterpreterBase<GrammarOptions, String> {
    public ConfigInterpreterOptions(String fileName) {
        super(fileName);
    }

    @Override
    protected void addConfiguration(Map<GrammarOptions, String> configMap, String key, String value) {
        try {
            GrammarOptions g = GrammarOptions.valueOf(key);
            configMap.put(g, value);
        } catch (IllegalArgumentException e) {
            Log.logReport("Unexpected key in options config file: " + fileName);
        }
    }
}

