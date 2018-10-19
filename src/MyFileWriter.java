import config.GrammarMain;
import config.Options;
import log.Log;

import java.io.*;

public class MyFileWriter {
    private BufferedWriter writer;

    public MyFileWriter(Options options) throws IOException {
        FileOutputStream outStream = new FileOutputStream(options.configMain.get(GrammarMain.OUT));
        this.writer = new BufferedWriter(new OutputStreamWriter(outStream));
    }

    public final void writeOutputFile(char[] buf) { //writeToFile
        try {
            writer.write(buf);
            writer.flush();
        } catch (IOException e) {
            Log.logReport("Writing output file error.");
            return;
        }
    }

    public final void close() {
        try {
            writer.close();
        } catch (IOException e) {

        }
    }
}
