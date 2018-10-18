import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFileReader {
    private BufferedReader reader;
    private Integer bufferSize;
    private Integer codeMode;

    public MyFileReader instance(Options options) {
        try {
            FileInputStream inStream = new FileInputStream(options.configMain.get(GrammarMain.Grammar.INPUT));
            this.reader = new BufferedReader(new InputStreamReader(inStream));
            this.bufferSize = Integer.parseInt(options.configOptions.get(GrammarOptions.Grammar.BUFFER_SIZE));
            this.codeMode = Integer.parseInt(options.configOptions.get(GrammarOptions.Grammar.CODE_MODE));
            return new MyFileReader();
        } catch (IOException e) {
            Log.logReport("Opening input file stream error.\n");
            return null;
        }
    }

    public final char[] readInputFile() { //readToBuffer
        char[] buf = new char[bufferSize];
        try {
            if (reader.read(buf) == -1)
                return null;
        } catch (IOException e) {
            Log.logReport("Reading input file error.\n");
            return null;
        }
        return buf;
    }

    public final void close() {
        try {
            reader.close();
        } catch (IOException e) {

        }
    }
}
