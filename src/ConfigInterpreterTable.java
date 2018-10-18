import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ConfigInterpreterTable implements ConfigInterpreter<Character, Integer> {
    String fileName;
    private final String DELIMITER = "=";

    //@Override
    public ConfigInterpreterTable(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public final void readConfiguration(Map<Character, Integer> freqTable) {
        String line;
        String[] set;
        int i = 0;
        char c;
        try {
            FileInputStream in = new FileInputStream(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(in));
            while ((line = buf.readLine()) != null) {
                set = line.split(DELIMITER);
                if (set.length == 1) {
                    Log.logReport("Syntax error in frequency table file");
                }
                c = set[0].charAt(0);
                freqTable.put(c, i = Integer.parseInt(set[1]));

            }
        } catch (Exception e) {
            Log.logReport("Error while reading configuration table file\n");
            return;
        }
        return;
    }
}
