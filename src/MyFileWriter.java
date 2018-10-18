import java.io.*;

public class MyFileWriter {
    private BufferedWriter writer;

    public MyFileWriter instance(Options options) {
        try {
            FileOutputStream outStream = new FileOutputStream(options.configMain.get(GrammarMain.Grammar.INPUT));
            this.writer = new BufferedWriter(new OutputStreamWriter(outStream));
            return new MyFileWriter();
        } catch (IOException e) {
            Log.logReport("Opening output file stream error.\n");
            return null;
        }
    }

    public final void writeOutputFile(char[] buf) { //writeToFile
        try {
            writer.write(buf);
            writer.flush();
        } catch (IOException e) {
            Log.logReport("Writing output file error.\n");
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
