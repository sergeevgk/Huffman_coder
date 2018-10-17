import java.util.Map;

public interface ConfigInterpreter {
    //не писать в интерфейсе public и abstract, это не нужно
    public abstract boolean readConfiguration(String fileName, Map config, Map configOptions, Map freqTable);
}
