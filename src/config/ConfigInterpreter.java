package config;

import java.util.Map;

public interface ConfigInterpreter<T, Q> {
    void readConfiguration(Map<T, Q> configMap);
}