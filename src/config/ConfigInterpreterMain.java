package config;

import log.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ConfigInterpreterMain extends ConfigInterpreterBase<GrammarMain, String> {
    public ConfigInterpreterMain(String fileName) {
        super(fileName);
    }
    @Override
    protected void addConfiguration(Map<GrammarMain, String> configMap, String key, String value) {
        try {
            GrammarMain g = GrammarMain.valueOf(key);
            configMap.put(g, value);
        } catch (IllegalArgumentException e) {
            Log.logReport("Unexpected key in main config file: " + fileName);
        }
    }
}
