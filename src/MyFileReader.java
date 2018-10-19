import config.GrammarMain;
import config.GrammarOptions;
import config.Options;
import log.Log;

import java.io.*;

public class MyFileReader {
    private BufferedReader reader;
    private Integer bufferSize;
    private Integer codeMode;

    public MyFileReader(Options options) throws IOException {
            FileInputStream inStream = new FileInputStream(options.configMain.get(GrammarMain.IN));
            this.reader = new BufferedReader(new InputStreamReader(inStream));
            this.bufferSize = Integer.parseInt(options.configOptions.get(GrammarOptions.BUFFER_SIZE));
            this.codeMode = Integer.parseInt(options.configOptions.get(GrammarOptions.CODE_MODE));
    }

    public final char[] readInputFile() { //readToBuffer
        char[] buf = new char[bufferSize];
        try {
            if (reader.read(buf) == -1)
                return null;
        } catch (IOException e) {
            Log.logReport("Reading input file error.");
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
