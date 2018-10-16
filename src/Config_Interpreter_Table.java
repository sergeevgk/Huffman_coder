import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Config_Interpreter_Table implements Config_Interpreter_Base{
    private final String DELIMITER = "=";
    private static final Character ALPHABET_START = 'a';
    public enum ALPHABET {A, B, C, D, E, F, G, H, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
    private static final Map<Character, ALPHABET> alphaMap;
    static {
        alphaMap = new HashMap<>();
        char c = ALPHABET_START;
        for (ALPHABET lexeme: ALPHABET.values()){
            alphaMap.put(c, lexeme);
            c += 1;
        }
    }
    public final boolean ReadConfiguration(String fileName, Map config, Map configOptions, Map freqTable){
        String line;
        String[] set;
        int i = 0;
        char c;
        try {
            FileInputStream in = new FileInputStream(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(in));
            while ((line = buf.readLine()) != null){
                set = line.split(DELIMITER);
                if (set.length == 1){
                    Log.logReport("Syntax error in frequency table file");
                }
                if (alphaMap.get(set[0].charAt(0)) == null){
                    Log.logReport("Invalid lexeme in frequency table file");
                }
                c = set[0].charAt(0);
                freqTable.put(c, i = Integer.parseInt(set[1]));

            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
