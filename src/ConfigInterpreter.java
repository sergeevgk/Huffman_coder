import java.util.Map;

public interface ConfigInterpreter<T, Q> {
    //ConfigInterpreter(String fileName);
    void readConfiguration(Map<T, Q> configMap);
}
